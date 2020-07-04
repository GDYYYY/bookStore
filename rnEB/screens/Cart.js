import React, {Component} from 'react';
import {
    View,
    Text,
    DeviceEventEmitter,
    AsyncStorage,
    Dimensions,
    Image,
    TouchableOpacity,
    StyleSheet, FlatList
} from 'react-native';
import {apiUrl} from "../urlconfig";
import BookCard from '../components/BookCard.js';
import {Button, Flex, Checkbox} from "@ant-design/react-native";

const url = apiUrl + 'getCart?u_id=';
const {width, height} = Dimensions.get('window');
const styles = StyleSheet.create({
    base: {
        height: height - 70,
        paddingTop: height / 20,
        padding: width / 80,
        alignItems: 'center',
        borderRadius: 5,
    },
    background: {
        backgroundColor: '#fff',
    },
    title: {
        color: '#0c4969',
        fontSize: 20,
        marginRight: width / 5
    },
    content: {
        // marginTop: width / 20,
        padding: width / 20,
    },
    bottom: {
        position: 'absolute',
        bottom: 0,
        padding: 20,
        height: 60,
        width: width,
        backgroundColor: 'rgba(255,255,255,0.9)',
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
    },
    button: {
        // color: 'red',
        borderWidth: 0,
    }
});
export default class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLog: false,
            u_id: '',
            carts: {},
            checked: {},
            books: {},
            totalPrice: 0,
            allchecked: 0
        };
    }

    componentDidMount() {
        const _this = this;
        this.subscription = DeviceEventEmitter.addListener('UPDATE_CART_DATA', _this.refreshData.bind(_this));
        DeviceEventEmitter.addListener('changeNumber', _this.refreshNum.bind(_this));
    };

    refreshData() {
        const _this = this;
        AsyncStorage.getItem('u_id').then(value => {
            _this.setState({u_id: value});
            console.log(_this.state.u_id);
            _this.onLoad(url + _this.state.u_id);
            _this.setState({isLog: true});
        });
    };

    refreshNum() {
        const _this = this;
        console.log(url);
        fetch(url + _this.state.u_id, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(responseData => {
                for (let i = 0; i < responseData.length; i++) {
                    responseData[i].checked = _this.state.carts[i].checked;
                }
                _this.setState({carts: responseData});
                console.log(responseData);
                _this.count();
            })
            .catch(error => {
                console.log(error);
            });
    }

    componentWillUnmount() {
        this.subscription.remove();
    };

    onLoad(url) {
        const _this = this;
        console.log(url);
        fetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(responseData => {
                responseData.forEach(i => i.checked = 0);
                _this.setState({carts: responseData});
            })
            .catch(error => {
                console.log(error);
            });
        fetch(apiUrl + 'getBooks', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(responseData => {
                _this.setState({books: responseData});
            })
            .catch(error => {
                console.log(error);
            });
    };

    count() {
        let sum = 0;
        let cart = this.state.carts;
        let b = this.state.books;
        cart.forEach(c => {
            if (c.checked == 1) {
                sum += c.num * b[c.b_id - 1].price;
            }
        });
        sum = sum.toFixed(2);
        this.setState({totalPrice: sum});
    }

    onChange(id) {
        let c = this.state.carts;
        let tmp = 1;
        for (let i = 0; i < c.length; i++) {
            if (c[i].id == id) {
                c[i].checked = (c[i].checked == 0) ? 1 : 0;
            }
            if (c[i].checked == 0) tmp = 0;
        }
        this.setState({carts: c, allchecked: tmp});
        this.count();
    }

    onAllChange() {
        let c = this.state.carts;
        let a = this.state.allchecked;
        let tmp = (a == 0) ? 1 : 0;
        for (let i = 0; i < c.length; i++) {
            c[i].checked = tmp;
        }
        this.setState({carts: c, allchecked: tmp});
        this.count();
    }

    onSubmit() {
        const _this = this;
        let u = _this.state.u_id;
        let total = _this.state.totalPrice;
        _this.setState({totalPrice: 0,allchecked:0});
        fetch(apiUrl + 'newForm?u_id=' + u + '&cost=' + total, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(responseData => {
                let f = responseData.f_id;
                _this.state.carts.forEach(c => {
                    if (c.checked == 1) {
                        console.log(c);
                        fetch(apiUrl + 'addOrders?f_id=' + f + '&u_id=' + u + '&num=' + c.num + '&b_id=' + c.b_id, {
                            method: 'POST',
                            credentials: 'include',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                        });
                        fetch(apiUrl + 'delCart?id=' + c.id, {
                            method: 'POST',
                            credentials: 'include',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                        }).then(
                            () => {
                                _this.onLoad(url + _this.state.u_id);
                                alert("购买成功！")
                            })
                    }
                })
            })
            .catch(error => {
                console.log(error);
            });

    }

    onDelete() {
        const _this = this;
        let u = _this.state.u_id;
        _this.setState({totalPrice: 0});
        _this.state.carts.forEach(c => {
            if (c.checked == 1) {
                fetch(apiUrl + 'delCart?id=' + c.id, {
                    method: 'POST',
                    credentials: 'include',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }).then(
                    () => {
                        _this.onLoad(url + _this.state.u_id);
                        alert("删除成功！")
                    })
            }
        })
    }

    renderItem = ({item}) => {
        return (
            <View>
                {item.checked == 0 && (<TouchableOpacity
                    onPress={() => {
                        this.onChange(item.id);
                    }}>
                    <Image style={{width: 20, height: 20, borderRadius: 10}}
                           source={require('../images/check.png')}
                    />
                </TouchableOpacity>)}
                {item.checked == 1 && (<TouchableOpacity
                    onPress={() => {
                        this.onChange(item.id);
                    }}>
                    <Image style={{width: 20, height: 20, borderRadius: 10}}
                           source={require('../images/checked.png')}
                    />
                </TouchableOpacity>)}
                <TouchableOpacity
                    onPress={() => {
                        const {navigate} = this.props.navigation;
                        navigate('Details', {book: item.b_id});
                    }}>
                    <BookCard b_id={item.b_id} num={item.num}/>
                </TouchableOpacity></View>
        );
    };

    render() {
        return (
            <View style={[styles.base]}>
                {!this.state.isLog && (<View style={[styles.content]}>
                    <Flex justify="center" direction="row">
                        <Text style={[styles.title]}>你还没有登录哦~~</Text>
                    </Flex>
                </View>)}
                {this.state.isLog && (<View style={[styles.content]}>
                    {(this.state.carts.length == 0) && <Flex justify="center" direction="row">
                        <Text style={[styles.title]}>你的购物车空空如也~</Text>
                    </Flex>}
                    <Flex justify="center">
                        <FlatList
                            data={this.state.carts}
                            keyExtractor={(item, index) => item.id}
                            renderItem={this.renderItem}
                            numColumns={1}
                        />

                    </Flex>
                </View>)}
                <View style={[styles.bottom]}>
                    {this.state.allchecked == 0 && (<TouchableOpacity
                        onPress={() => {
                            this.onAllChange();
                        }}>
                        <Image style={{width: 20, height: 20, borderRadius: 10}}
                               source={require('../images/check.png')}
                        />
                    </TouchableOpacity>)}
                    {this.state.allchecked == 1 && (<TouchableOpacity
                        onPress={() => {
                            this.onAllChange();
                        }}>
                        <Image style={{width: 20, height: 20, borderRadius: 10}}
                               source={require('../images/checked.png')}
                        />
                    </TouchableOpacity>)}
                    <Text>合计:￥{this.state.totalPrice}</Text>
                    <Button type="primary" style={{marginLeft: 120}} onPress={this.onSubmit.bind(this)}>购买</Button>
                    <Button danger size="small" style={[styles.button]} onPress={this.onDelete.bind(this)}>删除</Button>
                </View>
            </View>
        );
    }
}

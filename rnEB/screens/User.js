import React, {Component} from 'react';
import {
    View,
    Text,
    Dimensions,
    StyleSheet,
    AsyncStorage,
    DeviceEventEmitter,
    Image,
    TouchableOpacity
} from 'react-native';
import {Button, Flex} from "@ant-design/react-native";
import {apiUrl} from "../urlconfig";

const url = apiUrl + 'getuser?u_id=';
const {width, height} = Dimensions.get('window');
const styles = StyleSheet.create({
    base: {
        height: height,
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
        marginTop: width / 20,
        padding: width / 20,
    },
});
export default class User extends Component {
    constructor(props) {
        super(props);
        this.state = {
            avatar: 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=145692761,4091651670&fm=26&gp=0.jpg',
            isLog: false,
            u_id: '',
            user: {}
        };
    }

    componentDidMount() {
        const _this = this;
        this.subscription = DeviceEventEmitter.addListener('UPDATE_USER_DATA', _this.refreshData.bind(_this))
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
                _this.setState({user: responseData});
                // if(responseData.userIcon){
                console.log(responseData.userIcon.icon);
                _this.setState({avatar: responseData.userIcon.icon});
                // }
            })
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        const _this = this;
        return (
            <View>
                {/* style={[styles.base,styles.background]}>*/}
                {!this.state.isLog && (<View style={[styles.background, styles.content]}>
                    <Flex justify="center" direction="row">
                        <Text style={[styles.title]}>请先登录</Text>
                        <Button type="primary" onPress={() => {
                            const {navigate} = this.props.navigation;
                            navigate('Login');
                        }}>登录</Button>
                    </Flex>
                </View>)}
                {this.state.isLog && (<View style={[styles.background, styles.content]}>
                    <Flex justify="center" direction="row">
                        <Image style={{width: 66, height: 66, borderRadius: 33, margin: 5}}
                               source={{uri: _this.state.avatar}}
                        />
                        <Text style={[styles.title]}>{_this.state.user.username}</Text>
                        <Button type="primary" onPress={() => {
                            AsyncStorage.removeItem('u_id');
                            _this.setState({user:{},isLog: false,avatar:'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=145692761,4091651670&fm=26&gp=0.jpg'});
                        }}>退出</Button>
                    </Flex>
                    <Text style={{marginBottom:7,borderColor:'#0c4969', borderBottomWidth: 1,  opacity: 0.5}}/>
                    <Text style={{height:20}}/>
                    <Flex justify="start" direction="row">
                        <TouchableOpacity
                            onPress={() => {
                                const {navigate} = this.props.navigation;
                                // AsyncStorage.setItem('b_id',JSON.stringify(item.b_id)).then(()=>{navigate('Details');});
                                navigate('Order');
                            }}>
                        <Text style={[styles.title]}>过往订单</Text>
                        </TouchableOpacity>
                    </Flex>
                </View>)}
            </View>
        );
    }
}
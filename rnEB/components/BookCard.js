import React, {Component} from 'react';
import {
    Text,
    View,
    StyleSheet,
    Dimensions,
    Image,
    TouchableOpacity,
    Alert,
    DeviceEventEmitter,
    AsyncStorage
} from 'react-native';
import {Flex, Stepper} from "@ant-design/react-native";
import {apiUrl} from "../urlconfig";

const url = apiUrl + 'getBook?b_id=';
const {width, height} = Dimensions.get('window');
const styles = StyleSheet.create({
    base: {
        width: width,
        height: height/40*9,
        margin: width / 40,
        marginBottom: 0,
        padding: width / 20,
        // alignItems: 'center',
        borderRadius: 5,
    },
    background: {
        backgroundColor: '#fff',
    },
    price: {
        color: '#1989fa',
    },
    author: {
        color: 'rgb(96, 125, 163)',
    },
});
export default class BookCard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            book: {},
            u_id:0,
            num:props.num
        };
    }

    onLoad(url) {
        const _this = this;

        fetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(responseData => {
                responseData.image=responseData.bookInfo.image;
                _this.setState({book: responseData});
                _this.render();
            })
            .catch(error => {
                console.log(error);
            });
    }
    updatenum(){
        const _this=this;
        _this.forceUpdate();
        fetch(apiUrl+'getNum?u_id='+_this.state.u_id+'&b_id='+_this.props.b_id, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(resp => resp.json())
            .then(response => {
                console.log(response);
                _this.setState({num: response.num});
            })
            .catch(error => {
                console.log(error);
            });
    }
    componentDidMount() {
        const _this = this;
        AsyncStorage.getItem('u_id').then(value => {console.log(value);_this.setState({u_id:value})});
        this.onLoad(url + _this.props.b_id);
        DeviceEventEmitter.addListener('changeNumberinc',_this.updatenum.bind(_this));
        DeviceEventEmitter.addListener('changeCard',_this.onLoad(url + _this.props.b_id));
    }

    onChange(val){
        const _this=this;
        // let k=0;
        _this.setState({num:val});
        fetch(apiUrl + 'updateCart?u_id='+_this.state.u_id+'&b_id='+_this.props.b_id+'&num='+val, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(responseData => {
                DeviceEventEmitter.emit('changeNumber');
            })
            .catch(error => {
                console.log(error);
            });

    }

    render() {
        return (
            <View style={[styles.base, styles.background]}>
                <Flex justify="start" direction="row">
                <Image source={{uri: this.state.book.image}}
                       style={{width: width / 5, height: height / 6,marginLeft:width/14 }}></Image>
                <Flex  justify="center" direction="column" style={{width: width / 2}}>
                    <Text>{this.state.book.name}</Text>
                    <Text style={[styles.author]}>{this.state.book.author}</Text>
                    <Text style={[styles.price]}>￥{this.state.book.price}</Text>
                    <Stepper
                        min={1}
                        defaultValue={this.state.num}
                        value={this.state.num}
                        style={{marginLeft: width / 5}}
                        onChange={this.onChange.bind(this)}
                    />
                </Flex>

            </Flex>
            </View>
        );
    }
}
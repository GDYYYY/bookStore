import React, {Component} from 'react';
import {View, Text, TextInput, AsyncStorage, DeviceEventEmitter, StyleSheet, Dimensions} from 'react-native';
import {Button, Flex} from "@ant-design/react-native";
import {apiUrl} from "../urlconfig";

const url = apiUrl + 'login';
const {width, height} = Dimensions.get('window');
const styles = StyleSheet.create({
    base: {
        height: height/8*7,
        // paddingTop: height/20,
        // padding: width / 80,
        alignItems: 'center',
        borderRadius: 5,
    },
    background: {
        backgroundColor: '#fff',
    },
    content: {
        margin: width / 20,
        fontSize: 20,
    },
    bottom: {
        alignSelf: 'center',
    },
});
export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            u_id: '',
        };
    }

    usernamechange(username) {
        this.setState({username: username});
    }

    passwordchange(password) {
        this.setState({password: password});
    }

    login() {
        const _this = this;
        const {navigation} = this.props;
        fetch(url, {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "username": this.state.username,
                "password": this.state.password,
            }),
        })
            .then((response) => response.json())
            .then((responseData) => {
                alert("登陆成功");
                console.log(responseData);

                AsyncStorage.setItem('u_id', JSON.stringify(responseData.u_id));
                DeviceEventEmitter.emit('UPDATE_USER_DATA');
                DeviceEventEmitter.emit('UPDATE_CART_DATA');

                navigation.goBack();
            })
            .catch(error => {
                alert("用户名或密码错误");
                console.log(error);
            });
    }

    render() {
        return (
            <View>
                <Flex justify="center" direction="column" style={[styles.base]}>
                    <TextInput
                        style={[styles.content]}
                        placeholder={'请输入用户名'}
                        value={this.state.username}
                        onChangeText={this.usernamechange.bind(this)}
                        />
                        <TextInput
                            style={[styles.content]}
                            placeholder={'请输入密码'}
                            value={this.state.password}
                            secureTextEntry={true}
                            onChangeText={this.passwordchange.bind(this)}
                        />
                        <Flex justify="center" direction="row" style={[styles.bottom]}>
                            <Button type="primary" onPress={this.login.bind(this)} style={{margin:width/20}}>登录</Button>
                            <Button type="primary" style={{margin:width/20}}>注册</Button>
                        </Flex>
                </Flex>
            </View>);
    }
    }

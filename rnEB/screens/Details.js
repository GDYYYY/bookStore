import React, {Component} from 'react';
import {Text, View, StyleSheet, Dimensions, Image, ScrollView, TouchableOpacity} from 'react-native';
import {Flex, Button,Stepper} from "@ant-design/react-native";
import {apiUrl} from "../urlconfig";
const url = apiUrl + 'getBook?b_id=';
const {width, height} = Dimensions.get('window');
const styles = StyleSheet.create({
    base: {
        width: width,
        height: height / 32 * 31,
        margin: 0,
    },
    content: {
        padding: width / 20,
    },
    align: {
        alignSelf: 'center',
    },
    background: {
        backgroundColor: '#fff',
    },
    title: {
        color: '#000',
        fontSize: 20,
        fontWeight: 'bold',
        margin: 5
    },
    author: {
        fontSize: 15,
        color: 'rgb(96, 125, 163)',
        margin: 5
    },
    bottom: {
        // alignSelf: 'flex-end',
        padding: width / 20
    },
    price: {
        color: '#1989fa',
        fontSize: 20,
    },
});
export default class Details extends Component {
    constructor(props) {
        super(props);
        this.state = {
            book:{}
        };
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
                _this.setState({book: responseData});
            })
            .catch(error => {
                console.log(error);
            });
    };
    render() {
        const _this = this;
        let b_id = this.props.navigation.getParam('book');
        this.onLoad(url+b_id);

        const {navigation} = this.props;
        return (
            <View style={[styles.background, styles.base]}>
                <TouchableOpacity onPress={() => {
                    navigation.goBack()
                }}>
                    <Image style={{width: 36, height: 36}} source={require('../images/icon-return.png')}
                    /></TouchableOpacity>
                <ScrollView style={[styles.align, styles.content]}>
                    <Flex justify="center" direction="column">
                        <Image source={{uri: _this.state.book.image}}
                               style={{width: width / 3, height: height / 4, marginBottom: height / 10}}></Image>
                        <Text style={[styles.title]}>{_this.state.book.name}</Text>
                        <Text style={[styles.author]}>{_this.state.book.author}</Text>
                        <Text>       {_this.state.book.description}</Text>
                    </Flex>
                </ScrollView>
                <View style={[styles.bottom]}>
                    <Text style={[styles.price, styles.bottom]}>￥{_this.state.book.price}</Text>
                    <Flex justify="center" direction="row" >
                    <Stepper
                        max={10}
                        min={1}
                        defaultValue={1}
                        style={{marginRight: width/5}}
                        // onChange={}
                    />
                        <Button type="primary">加入购物车</Button>
                    </Flex>
                </View>
            </View>
        );
    }
}
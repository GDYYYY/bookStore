import React, {Component} from 'react';
import {Text, View, StyleSheet, Dimensions, Image,TouchableOpacity} from 'react-native';
const {width, height} = Dimensions.get('window');
const styles = StyleSheet.create({
    base: {
        width: width/5*2,
        // height: height/4,
        margin:width/40,
        marginBottom:0,
        padding:width/80,
        alignItems:'center',
        borderRadius:5,
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
export default class HomeCard extends Component {

    render() {
        return (
            <View style={[styles.base,styles.background]}  >
                <Image source={{uri: this.props.book.bookInfo.image}} style={{width: width/10*3,height:height/5}} ></Image>
                <Text>{this.props.book.name}</Text>
                <Text style={[styles.author]}>{this.props.book.author}</Text>
                <Text style={[styles.price]}>￥{this.props.book.price}</Text>
            </View>
        );
    }
}
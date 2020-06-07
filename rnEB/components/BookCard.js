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
export default class HomeCard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            book: {},
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
                _this.setState({book: responseData});
                _this.render();
            })
            .catch(error => {
                console.log(error);
            });
    }

    componentDidMount() {
        const _this = this;
        this.onLoad(url + _this.props.b_id);
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
                        max={10}
                        min={1}
                        defaultValue={this.props.num}
                        style={{marginLeft: width / 5}}
                        // onChange={}
                    />
                </Flex>

            </Flex>
            </View>
        );
    }
}

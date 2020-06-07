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
import {Button, Flex} from "@ant-design/react-native";

const url = apiUrl + 'getCart?u_id=';
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
export default class Cart extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLog: false,
      u_id: '',
      carts: {},
    };
  }
  componentDidMount() {
    const _this = this;
    this.subscription = DeviceEventEmitter.addListener('UPDATE_CART_DATA', _this.refreshData.bind(_this))
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
            _this.setState({carts: responseData});
        })
        .catch(error => {
          console.log(error);
        });
  };
  renderItem = ({item}) => {
    return(
        <TouchableOpacity
            onPress={() => {
              const {navigate} = this.props.navigation;
              navigate('Details',{book:item.b_id});
            }}>
          <BookCard b_id={item.b_id} num={item.num} />
          {/*<Text>{item.b_id}</Text>*/}
        </TouchableOpacity>
    );
  };
  render() {
    return (
      <View>
        {!this.state.isLog && (<View style={[ styles.content]}>
          <Flex justify="center" direction="row">
            <Text style={[styles.title]}>你还没有登录哦~~</Text>
          </Flex>
        </View>)}
        {this.state.isLog && (<View style={[styles.content]}>
          {/*<Flex justify="center" direction="row">*/}
          {/*  <Text style={[styles.title]}>快去看看吧~{this.state.u_id}号用户</Text>*/}
          {/*</Flex>*/}
          <Flex justify="center">
            <FlatList
                data={this.state.carts}
                keyExtractor={(item, index) => index}
                renderItem={this.renderItem}
                numColumns={1}
            />
          </Flex>
        </View>)}
      </View>
    );
  }
}

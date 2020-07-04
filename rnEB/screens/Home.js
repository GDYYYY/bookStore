import React, {Component} from 'react';
import {View, Text, Image, Dimensions, FlatList, ScrollView, Alert, TouchableOpacity, AsyncStorage} from 'react-native';
import HomeCard from '../components/HomeCard.js';
import {SearchBar, WhiteSpace, WingBlank, Flex} from '@ant-design/react-native';
import {apiUrl} from "../urlconfig";

const {width, height} = Dimensions.get('window');
export default class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            target:''
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
                var res=[];
                responseData.forEach(b=>{
                    if(b.onshelf===1)
                        res.push(b);
                });
                _this.setState({books: res});
            })
            .catch(error => {
                console.log(error);
            });
    }

    renderItem = ({item}) => {
        return (
            <TouchableOpacity
                onPress={() => {
                    const {navigate} = this.props.navigation;
                    // AsyncStorage.setItem('b_id',JSON.stringify(item.b_id)).then(()=>{navigate('Details');});
                    navigate('Details', {book: item.b_id});
                }}>
                <HomeCard book={item}/>
            </TouchableOpacity>);
    }

    componentDidMount() {
        this.onLoad(apiUrl + 'getBooks');
    }

    clear(value) {
        if(value)
            this.setState({target: ''});
    }
    onChange(value){
        this.setState({target: value});
    }

    render() {
        return (
            <View style={{margin: width / 20}}>
                <SearchBar value={this.state.target}
                           placeholder="search.."
                           onSubmit={value => Alert.alert(value)}
                           // onSubmit={value => this.search(value)}
                           onCancel={value =>this.clear(value)}
                           onChange={value =>this.onChange(value)}
                />
                <ScrollView>
                    <Image source={{uri: 'http://img61.ddimg.cn/upload_img/00838/cxtc/750x315-1583737502.jpg'}}
                           style={{width: width, height: height / 6}}></Image>
                    <Flex justify="center">
                        <FlatList
                            data={this.state.books}
                            keyExtractor={(item, index) => index}
                            renderItem={this.renderItem}
                            numColumns={2}
                        />
                    </Flex>
                </ScrollView>
            </View>
        );
    }
}

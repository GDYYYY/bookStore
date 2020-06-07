import {createStackNavigator} from 'react-navigation-stack';
import {createAppContainer, createSwitchNavigator} from 'react-navigation';
import {createBottomTabNavigator} from 'react-navigation-tabs';
import React, {Component} from 'react';
import {Platform,Image} from 'react-native';
console.disableYellowBox = true;
import Home from './screens/Home';
import Login from './screens/Login';
import Cart from './screens/Cart';
import Order from './screens/Order';
import User from './screens/User';
import Details from "./screens/Details";

export const BottomTab = createAppContainer(
  createBottomTabNavigator(  //底部导航栏
    {
      Home: {
        screen: Home,
        navigationOptions: {
          tabBarLabel: '首页',
          tabBarIcon: ({tintColor, focused}) => (
              <Image source={require('./images/icon-home.png')}  style={{width: 26, height: 26}}></Image>
          ),
        },
      },
      Cart: {
        screen: Cart,
        navigationOptions: {
          tabBarLabel: '购物车',
          tabBarIcon: ({tintColor, focused}) => (
              <Image source={require('./images/icon-cart.png')} style={{width: 26, height: 26}}></Image>
          ),
        },
      },
      User: {
        screen: User,
        navigationOptions: {
          tabBarLabel: '我的',
          tabBarIcon: ({tintColor, focused}) => (
              <Image source={require('./images/icon-user.png')} style={{width: 26, height: 26}}></Image>
          ),
        },
      },

    },
    {
      tabBarOptions: {
        activeTintColor: '#06C1AE',
      },
    },
  ),
);
//堆栈式路由
const AppRouter = createStackNavigator({
  Bottom: {
    screen: BottomTab,
    navigationOptions: {
      header: null,
    },
  },
    Details: {
        screen: Details,
        navigationOptions: {
            header: null,
        },
    },
    Login: {
        screen: Login,
        navigationOptions: {
            header: null,
        },
    },
    Order: {
        screen: Order,
        navigationOptions: {
            header: null,
        },
    },
});

export const App = createAppContainer(
  createSwitchNavigator(
    {
      AppRouter: AppRouter,
    },
    {
      initialRouteName: 'AppRouter',
    },
  ),
);

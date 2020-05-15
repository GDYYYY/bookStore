const app = getApp();
import Toast from '@vant/weapp/toast/toast';
Page({
  data: {
    user:
    {
      "username": "",
      "password": ""
    }
  },

  getName: function (event) {
    console.log(event.detail);
    this.setData({ "user.username": event.detail })
    console.log(this.data.user.username);
  },

  getCode: function (event) {
    var that = this;
    this.setData({ "user.password": event.detail })
  },

  signIn: function () {
    var that = this;
    wx.request({
      url: 'http://localhost:8080/login',
      method: "POST",
      data: {
        username: this.data.user.username,
        password: this.data.user.password
        // user:this.data.user
      },
      header: {
        'content-type': 'application/json'
      },
      success(res) {
        if (res.data) {
          app.globalData.isLog = true;
          app.globalData.user = res.data;
          Toast.success('登录成功！');
          wx.switchTab({
            url: '../home/home'
          })
        } else {
          Toast.fail('用户名或密码错误！');
        }
      }
    });
  },

  signUp: function (event) {
    // wx.navigateTo({
    //   url: '../login/login',
    // })
    Toast('只有聪明的人才能注册');
  },

})

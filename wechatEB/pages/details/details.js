const app=getApp();
import Toast from '@vant/weapp/toast/toast';
Page({
  data: {
      book:[]
  },

  onLoad: function (options) {
    var that = this;
    console.log(app.globalData.b_id);
    wx.request({
      url: 'http://localhost:8080/getBook?b_id='+ app.globalData.b_id,
      method: "GET",
      header: {
        'content-type': 'application/json',
      },
      success(resp) {
        console.log(resp);
        that.setData({
          book:resp.data
        });
      }
    })
  },
  addCart: function (event) {
    // wx.navigateTo({
    //   url: '../login/login',
    // })
    Toast('呀，你没钱，不卖');
  },
})
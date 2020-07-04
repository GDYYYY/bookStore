const app=getApp();
import Toast from '@vant/weapp/toast/toast';
Page({
  data: {
      book:[],
      num:'1'
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
  onChange:function(event){
    this.setData({num:event.detail});
    // Toast(this.data.num);
  },
  addCart: function (event) {
    // wx.navigateTo({
    //   url: '../login/login',
    // })
    console.log(app.globalData.user);
    if(!app.globalData.user.u_id)
    Toast('请先登录');
    else{
    wx.request({
      url: 'http://localhost:8080/addCart?u_id='+ app.globalData.user.u_id+'&b_id='+app.globalData.b_id+'&num='+this.data.num,
      method: "POST",
      header: {
        'content-type': 'application/json',
      },
      success(resp) {
        console.log(resp);
        Toast('添加成功！');
        // that.setData({
        //   book:resp.data
        // });
      }
    })}
  },
})
const app=getApp();
Page({
  data: {
      user:{},
      isLog:false,
      u_id:0
  },
  onShow: function (options) {
    var that = this;
    console.log("userid");
    this.setData({user:app.globalData.user});
    console.log(app.globalData.user.u_id);
    console.log(this.data.user.u_id);
    this.setData({isLog:app.globalData.isLog});
    this.setData({u_id:app.globalData.u_id});
    if(app.globalData.isLog)
    {that.data.user=app.globalData.user}
  },
  toLog: function (event) {
    // console.log(event.currentTarget.dataset.b_id);
    // app.globalData.b_id=event.currentTarget.dataset.b_id;
    wx.navigateTo({
      url: '../login/login',
    })
  },
  Logout: function () {
    app.globalData.isLog=false,
    app.globalData.user={},
    wx.switchTab({
      url: '../home/home'
    })
  },
})
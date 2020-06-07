const app = getApp()

Page({
  data: {
    books:[],
    target:"",
    isLog:false
  },
  onShow: function (options) {
    var that = this;
    this.setData({isLog:app.globalData.isLog});
    this.setData({u_id:app.globalData.u_id});
      if(app.globalData.isLog)
      {wx.request({
        url: 'http://localhost:8080/getCart?u_id='+app.globalData.user.u_id,
        method: "POST",
        data: {
        },
        header: {
          'content-type': 'application/json', 
        },
        success(res) {
          console.log(res);
          that.setData({
            books: res.data
          })
        }
      });}
  },
  //详情
  toDetails: function (event) {
    console.log(event.currentTarget.dataset.b_id);
    app.globalData.b_id=event.currentTarget.dataset.b_id;
    wx.navigateTo({
      url: '../details/details',
    })
  },
})

const app = getApp()

Page({
  data: {
    books:[],
    target:""
  },
  onLoad: function (options) {
    var that = this;
      wx.request({
        url: 'http://localhost:8080/getBooks',
        method: "POST",
        data: {
        },
        header: {
          'content-type': 'application/json', 
        },
        success(res) {
          console.log(res);
          var resp=[];
          res.data.forEach(element => {
            if(element.onshelf==1)
            resp.push(element);
          });
          that.setData({
            books:resp
          })
        }
      });
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
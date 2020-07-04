Component({
  properties: {
    b_id:{
      type: String,
      value: "",
    }
  },
  data: {
    url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
    title: "title",
    author: "author",
    price: "price",
    u_id: "",
    // b_id: "",
    num: "",
  },
  ready:function () {
    var that = this;
    console.log(that.properties.b_id);
    wx.request({
      url: 'http://localhost:8080/getBook?b_id='+ that.properties.b_id,
      method: "GET",
      header: {
        'content-type': 'application/json',
      },
      success(resp) {
        console.log(resp);
        var u=that.url;
        if(resp.data.bookInfo)
        u=resp.data.bookInfo.image;
        that.setData({
          author: resp.data.author+'\n',
          title: resp.data.name+'\n',
          url: u,
          price: resp.data.price,
        });
        console.log("sss");
      }
    })
  },

})
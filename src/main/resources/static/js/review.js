'use strict';
$(function () {
  $(document).on('click', '.good-button', function(){
    var thisCount = $('.a').html();
    thisCount=Number(thisCount);
    thisCount=thisCount +1;
    $('.a').html(thisCount);
  })
 /* $(document).on('submit', '.count', function () {

    $.ajax({
      url: "http://localhost:8080/count-good"
      , type: 'POST'
      , dataType: 'json'
      , data: {
        _csrf: $("*[name=_csrf]").val()  // CSRFトークンを送信
        ,count: $('.good-button').val()
      }
    }).done(function (data) {
      console.log("通信成功！！")
      $('.a').append('<div>${data.count}</div>')
      $('.good-button').val("");
    }).fail(function () {
      console.log("通信失敗、、、")
    }).always(function () {
      console.log("通信終了")
    })
  })*/

})


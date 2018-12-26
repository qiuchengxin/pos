function myajax(data) {
    $.ajax({
        url:"/team/members",
        type:"post",
        dataType:"json",
        traditional: true,
        data:{"data":data},
        success:function (data) {
            console.log(data);
        }
    })
}
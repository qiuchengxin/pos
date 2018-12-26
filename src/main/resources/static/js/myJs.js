function myajax(data) {
    $.ajax({
        type:"POST",
        url:"/team/members",
        traditional: true,
        data:data,
        dataType: 'json',
        success:function (data) {
            console.log(data)
        },
        error:function (res) {
            console.log("error")
        },
    });
}

$(function(){
    $('.manageleft_p_father').click(function(){
        let a=$('.manageleft_upload').css('display');
        if(a==='none'){//如果当前隐藏
            $('.manageleft_upload').show();//那么就显示div
        }else{//否则
            $('.manageleft_upload').hide();//就隐藏div
        }
    })
})


//分页获取新闻
function fetchnews(pageNum) {
    //分页获取新闻接口
    let url1 = '/api/news/pageNum=' + pageNum;
    //获取新闻总页数
    let url2 = '/api/news/pageSum';

    $.ajax(url2, {
        method: 'GET',
        success: (data) => {
            $('#pageSum').text("共" + data.data + "页");


        }

    });


    $.ajax(url1,
        {
            method: 'GET',
            success: function (data) {

                let data1 = data.data.data;
                let html1 = '';
                for (i = 0; i < data1.length; i++) {
                    html1 +=
                        `
                                    <tr class="text-c">
                    <td>${data1[i].newsId}</td>
                    <td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','article-zhang.html','10001')" title="查看"><a href="/yunding/news.html?newsId=${data1[i].newsId}">${data1[i].newsTitle}</u></td>
                    <td> ${data1[i].newsPlace}</td>
                    <td>${getMoth(data1[i].newsUpdatedAt)}</td>
                    <td class="f-14 td-manage"> <a style="text-decoration:none" class="ml-5" onclick="window.location='/ueditor/news.html?id='+${data1[i].newsId} "  title="修改"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onclick="confirmDeleteNews(${data1[i].newsId},${pageNum})"  title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                </tr>
                `;
                    $('#newsTbody').html(html1);
                     $('#pageIndex').text("第" + pageNum + "页");


                }

            }
        })
    let html2=`<button onclick="newsPrePage()" class="btn btn-primary" style='margin-left:100px'>上一页</button>
				<button onclick="newsNextPage()" style="margin-right: 900px;" class="btn btn-primary">下一页</button>
				<span id="pageIndex">第一页</span><span>/</span><span id="pageSum">第  页</span>`;

    $('.manageright_bottom_items').html(html2);

}


//新闻分页
{


    let pageNum = 1;


    function newsPrePage() {
        let maxpageNum = $('#pageSum').text().match(/\d+/);

        pageNum--;
        if (pageNum < 1) {
            pageNum = 1;
        }
        ;
        fetchnews(pageNum);

    }

    function newsNextPage() {
        pageNum++;
        let maxpageNum = $('#pageSum').text().match(/\d+/);
        if (pageNum > maxpageNum) {
            pageNum = Number(maxpageNum);
        }
        ;
        fetchnews(pageNum);


    }

}


//新闻删除

function deleteNews(id, e) {
    let url = '/api/news/delete';
    $.ajax(url, {
        method: 'POST',
        data: {
            newsId: id
        },
        success: (data) => {
        }
    });
    fetchnews(e);
}

//新闻删除弹框
{
    function confirmDeleteNews(id, e) {
        if (confirm("确定删除该条新闻?")) {
            deleteNews(id, e)
        }
    }

}

//新闻修改
function news_onload(id) {
    let url = '/api/news/id?newsId=' + id;
    $.ajax(url, {
        method: 'GET',
        success: (data) => {
            let data1 = data.data.data;
            $("select[name='newsPlace']").val(data1.newsPlace);
            $("input[name='newsTitle']").val(data1.newsTitle);
            $("input[name='newsIntroduce']").val(data1.newsIntroduce);
            $("input[name='newsSender']").val(data1.newsSender);
            ue.setContent(data1.newsContent);
            $('.bottomdiv').html(' <input type = "button"  value = "修改" onClick = "newupdate(window.location.search.substr(window.location.search.lastIndexOf(\'=\')+1))" class="btn btn-secondary radius"/ >   <input type = "button" id = "submit3" value = "返回" onClick = "window.location=\'/H-ui.admin/news-list.html\'" class="btn btn-secondary radius"/ >')
        }
    })
}

//新闻修改按钮
function newupdate(id) {
    let url = '/api/news/update';
    let content = ue.getContent();
    let place = $("select[name='newsPlace']").val();
    let title = $("input[name='newsTitle']").val();
    let introduce = $("input[name='newsIntroduce']").val();
    let sender = $("input[name='newsSender']").val();
    let pic = $("input[name='pic']").val();

    if (content === "" || content == null || content == undefined) {
        alert("请输入内容！");
        return false;
        /*阻止表单提交*/
    } else if (place == "" || place == null || place == undefined) {
        alert("请选择位置！");
        return false;
        /*阻止表单提交*/
    } else if (title == "" || title == null || title == undefined) {
        alert("请输入标题!");
        return false;
        /*阻止表单提交*/
    } else if (introduce == "" || introduce == null || introduce == undefined) {
        alert("请输入简介!");
        return false;
        /*阻止表单提交*/
    } else if (sender == "" || sender == null || sender == undefined) {
        alert("请输入编辑者!");
        return false;
        /*阻止表单提交*/
    }


    else {

        let a = new FormData();
        a.append("pic", $("#fileId")[0].files[0]);
        a.append("newsIntroduce", introduce);
        a.append("newsTitle", title);
        a.append("newsPlace", place);
        a.append("newsSender", sender);
        a.append("newsContent", content);
        a.append("newsId", id);
        $.ajax({
            url: "/api/news/update",
            xhrFields: {
                withCredentials: true
            },
            type: "POST",
            cache: false,
            data: a,
            processData: false,
            contentType: false,
            async: false,
            success: function (result) {
                alert(result.data);
            },
            false: function (result) {
                alert(result.message);
            }
            //cache 上传文件不需要缓存，所以设置false
            //processData 因为data值是FormData对象，不需要对数据处理
            //contentType 因为是由form表单构造的FormData对象，且已声明了属性enctype，所以为false
        })
    }
}

/**
 * 文章js
 * @param pageNum
 */








//文章部分








//获取分页文章
function fetcharticle(pageNum) {
    let url = '/api/article/get';
    //获取article总页数
    let url2 = '/api/article/pageSum';
    $.ajax(url2, {
        method: 'GET',
        success: (data) => {
            $('#pageSum').text("共" + data.data + "页");
        }

    });
    $.ajax(url, {
        method: "POST",
        data: {
            pageNum: pageNum,
            articleCategoryId: null,
        },
        success: function (data) {
            let html1 = '';
            let data1 = data.data;
            for (i = 0; i < data1.length; i++) {
                html1 +=
                    `
                <tr class="text-c">
                    
                    <td>${data1[i].articleId}</td>
                    <td> ${getArticleCategoryId(data1[i].articleCategoryId)}</td>
                    <td><a href="javascript:;" onClick="picture_edit('图库编辑','picture-show.html','10001')"><img width="210" class="picture-thumb" src="${data1[i].articleImage}"></a></td>
                    <td class="text-l"><a class="maincolor" href="javascript:;" onClick="picture_edit('图库编辑','picture-show.html','10001')"><a href="/yunding/article.html?articleId=${data1[i].articleId}">${data1[i].articleIntroduce}</a></a></td>
                    <td>${getMoth(data1[i].articleUpdatedAt)}</td>
                    <td class="td-status"><span class="label label-success radius">已发布</span></td>
                    <td class="f-14 td-manage"> <a style="text-decoration:none" class="ml-5" onclick="window.location='/ueditor/article.html?id='+${data1[i].articleId} "  title="修改"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onclick="confirmDeleteArticle(${data1[i].articleId},${pageNum})"  title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                </tr>
                `;
                $('#articleTbody').html(html1);
                $('#pageIndex').text("第" + pageNum + "页");

            }
        }

    })
    let html2=`<button onclick="articlePrePage()" class="btn btn-primary">上一页</button>
				<button onclick="articleNextPage()" style="margin-right: 600px;" class="btn btn-primary">下一页</button>
				<span id="pageIndex">第 页</span><span>/</span><span id="pageSum">第  页</span>`;
    $('.manageright_bottom_items').html(html2);
}
//文章分页
{


    let pageNum = 1;


    function articlePrePage() {
        let maxpageNum = $('#pageSum').text().match(/\d+/);

        pageNum--;
        if (pageNum < 1) {
            pageNum = 1;
        }
        ;
        fetcharticle(pageNum);

    }

    function articleNextPage() {
        pageNum++;
        let maxpageNum = $('#pageSum').text().match(/\d+/);
        if (pageNum > maxpageNum) {
            pageNum = Number(maxpageNum);

        };
        fetcharticle(pageNum);


    }

}


//文章删除

function deleteArticle(id, e) {
    let url = '/api/article/delete';
    $.ajax(url, {
        method: 'POST',
        data: {
            articleId: id
        },
        success: (data) => {
            alert(message);
        }
    });
    fetcharticle(e);
}

//文章删除弹框
{
    function confirmDeleteArticle(id, e) {
        if (confirm("确定删除该条新闻?")) {
            deleteArticle(id, e)
        }
    }

}



//将类型转化为汉字
function getArticleCategoryId(articleCategoryId){
    let a;
    if(articleCategoryId===1){
        a="教程";
    }else if(articleCategoryId===2){
        a="文章";
    }else if(articleCategoryId===3){
        a="观点";
    }else if(articleCategoryId===4){
        a="咨询";
    }else{
        a="作品";
    }
    return a;
}


//文章修改

function article_onload(id) {
    let url = '/api/article/getById';
    $.ajax(url, {
        method: 'GET',
        data:{
          articleId:id,
        },
        success: (data) => {
            console.log(data);
            $("select[name='articleCategoryId']").val(data.data.articleCategoryId);
            $("input[name='atricleIntroduce']").val(data.data.articleIntroduce);
            $("input[name='articleSender']").val(data.data.articleSender);
            $("input[name='pic']").val(data.data.pic);
            ue.setContent(data.data.articleContent);
            $('.bottomdiv').html(' <input type = "button"  value = "修改" onclick = "articleupdate(window.location.search.substr(window.location.search.lastIndexOf(\'=\')+1))" class="btn btn-secondary radius"/ >   <input type = "button" id = "submit3" value = "返回" onClick = "window.location=\'/H-ui.admin/article-list.html\'" class="btn btn-secondary radius"/ >')
        }
    })
}

//文章修改按钮
function articleupdate(id) {

    let content = ue.getContent();
    let place = $("select[name='articleCategoryId']").val();
    let title = $("input[name='atricleIntroduce']").val();
    let sender = $("input[name='articleSender']").val();
    let pic = $("input[name='pic']").val();

    if (content === "" || content == null || content == undefined) {
        alert("请输入内容！");
        return false;
        /*阻止表单提交*/
    } else if (place == "" || place == null || place == undefined) {
        alert("请选择位置！");
        return false;
        /*阻止表单提交*/
    } else if (title == "" || title == null || title == undefined) {
        alert("请输入标题!");
        return false;
        /*阻止表单提交*/
    }else if (sender == "" || sender == null || sender == undefined) {
        alert("请输入编辑者!");
        return false;
        /*阻止表单提交*/
    }


    else {

        let a = new FormData();
        a.append("pic", $("#fileId")[0].files[0]);
        a.append("atricleIntroduce", title);
        a.append("articleCategoryId", place);
        a.append("articleSender", sender);
        a.append("articleContent", content);
        a.append("articleId", id);
        $.ajax({
            url: "/api/article/change",
            xhrFields: {
                withCredentials: true
            },
            type: "POST",
            cache: false,
            data: a,
            processData: false,
            contentType: false,
            async: false,
            success: function (result) {
                alert(result.data);
            },
            false: function (result) {
                alert(result.message);
            }
            //cache 上传文件不需要缓存，所以设置false
            //processData 因为data值是FormData对象，不需要对数据处理
            //contentType 因为是由form表单构造的FormData对象，且已声明了属性enctype，所以为false
        })
    }
}









//通知部分




//获取分页通知
function fetchinform(pageNum) {
    let url = '/api/inform/pageNum='+pageNum;
    //获取article总页数
    let url2 = '/api/inform/pageSum';

    $.ajax(url2, {
        method: 'GET',
        success: (data) => {

            $('#pageSum').text("共" + data.data + "页");
        }

    });
    $.ajax(url, {
        method: "GET",
        success: function (data) {
            let html1 = '';
            let data1 = data.data.data;
            for (i = 0; i < data1.length; i++) {
                html1 +=
                    `
                    <tr class="text-c">
                      <td>${data1[i].informId}</td>
                      <td>${data1[i].informContent}</td>
                      <td class="td-manage">  <a style="text-decoration:none" class="ml-5" onclick="confirmDeleteInform(${data1[i].informId},${pageNum})" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>          </tr>
                `;
                $('#informTbody').html(html1);
                $('#pageIndex').text("第" + pageNum + "页");

            }
        }

    })
    let html2=`<button onclick="informPrePage()" class="btn btn-primary">上一页</button>
				<button onclick="informNextPage()" style="margin-right: 600px;" class="btn btn-primary">下一页</button>
				<span id="pageIndex">第 页</span><span>/</span><span id="pageSum">第  页</span>`;
    $('.manageright_bottom_items').html(html2);
}
//通知分页
{


    let pageNum = 1;


    function informPrePage() {
        let maxpageNum = $('#pageSum').text().match(/\d+/);

        pageNum--;
        if (pageNum < 1) {
            pageNum = 1;
        }
        ;
        fetchinform(pageNum);

    }

    function informNextPage() {
        pageNum++;
        let maxpageNum = $('#pageSum').text().match(/\d+/);
        if (pageNum > maxpageNum) {
            pageNum = Number(maxpageNum);

        };
        fetchinform(pageNum);


    }

}


//通知删除

function deleteInform(id, e) {
    let url = '/api/inform/delete';
    $.ajax(url, {
        method: 'GET',
        data: {
            informId: id
        },
        success: (data) => {
            alert(message);
        }
    });
    fetchinform(e);
}

//通知删除弹框

    function confirmDeleteInform(id, e) {
        if (confirm("确定删除该条新闻?")) {
            deleteInform(id, e)
        }
    }







//作品部分

//分页获取作品
function fetchwork(pageNum) {

    let url = '/api/work/pageNum=' + pageNum;

    //获取work总页数
    let url2 = '/api/work/pageSum';

    $.ajax(url2, {
        method: 'GET',
        success: (data) => {
            $('#pageSum').text("共" + data.data + "页");
        }

    });
    $.ajax(url,
        {
            method: 'GET',
            success: function (data) {
                let data1 = data.data.data;
                let html1 = '';
                for (i = 0; i < data1.length; i++) {
                    html1 +=
                        `
                    <tr class="text-c va-m">
                        <td>${data1[i].workId}</td>
                        <td><a onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img width="200" class="product-thumb" src="${data1[i].workImage}"></a></td>
                        <td class="text-l"><a style="text-decoration:none" onClick="product_show('哥本哈根橡木地板','product-show.html','10001')" href="javascript:;"><img title="国内品牌" src=""> ${data1[i].workName}</a></td>
                        <td class="text-l">${data1[i].workIntroduce}</td>
                        <td class="f-14 td-manage"> <a style="text-decoration:none" class="ml-5" onclick="window.location='/ueditor/work.html?id='+${data1[i].workId} "  title="修改"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onclick="confirmDeleteWork(${data1[i].workId},${pageNum})"  title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                    </tr>
                        
                `;
                    $('#productBody').html(html1);
                    $('#pageIndex').text("第" + pageNum + "页");
                }
            }
        })
    let html2=`<button onclick="workPrePage()" class="btn btn-primary">上一页</button>
				<button onclick="workNextPage()" style="margin-right: 600px;" class="btn btn-primary">下一页</button>
				<span id="pageIndex">第 页</span><span>/</span><span id="pageSum">第  页</span>`;
    $('.manageright_bottom_items').html(html2);

}




//作品分页
{

    let pageNum = 1;


    function workPrePage() {
        let maxpageNum = $('#pageSum').text().match(/\d+/);

        pageNum--;
        if (pageNum < 1) {
            pageNum = 1;
        }
        ;
        fetchwork(pageNum);

    }

    function workNextPage() {
        pageNum++;
        let maxpageNum = $('#pageSum').text().match(/\d+/);
        if (pageNum > maxpageNum) {
            pageNum = Number(maxpageNum);
        }
        ;
        fetchwork(pageNum);


    }

}


//作品删除

function deletework(id, e) {
    let url = '/api/work/delete';
    $.ajax(url, {
        method: 'POST',
        data: {
            workId: id
        },
        success: (data) => {
        }
    });
    fetchwork(e);
}

//新闻删除弹框
{
    function confirmDeleteWork(id, e) {
        if (confirm("确定删除该条新闻?")) {
            deletework(id, e)
        }
    }

}

//作品修改
function work_onload(id) {

    let url = '/api/work/selectById';
    $.ajax(url, {
        method: 'POST',
        data:{
            workId:id,
        },
        success: (data) => {

            let data1=data.data.data;
            $("input[name='workName']").val(data1.workName);
            $("input[name='workIntroduce']").val(data1.workIntroduce);
            $("input[name='workSender']").val(data1.workSender);
            $("input[name='pic']").val(data1.pic);
            ue.setContent(data1.workContent);
            $('.bottomdiv').html(' <input type = "button"  value = "修改" onclick = "workupdate(window.location.search.substr(window.location.search.lastIndexOf(\'=\')+1))" class="btn btn-secondary radius"/ >   <input type = "button" id = "submit3" value = "返回"  onClick = "window.location=\'/H-ui.admin/products-list.html\'" class="btn btn-secondary radius"/ >')
        }
    })
}

//作品修改按钮
function workupdate(id) {

    let content = ue.getContent();
    let title = $("input[name='workName']").val();
    let introduce = $("input[name='workIntroduce']").val();
    let sender = $("input[name='workSender']").val();
    let pic = $("input[name='pic']").val();

    if (content === "" || content == null || content == undefined) {
        alert("请输入内容！");
        return false;
        /*阻止表单提交*/
    } else if (title == "" || title == null || title == undefined) {
        alert("请输入标题!");
        return false;
        /*阻止表单提交*/
    } else if (introduce == "" || introduce == null || introduce == undefined) {
        alert("请输入简介!");
        return false;
        /*阻止表单提交*/
    } else if (sender == "" || sender == null || sender == undefined) {
        alert("请输入编辑者!");
        return false;
        /*阻止表单提交*/
    }


    else {

        let a = new FormData();
        a.append("pic", $("#fileId")[0].files[0]);
        a.append("workIntroduce", introduce);
        a.append("workName", title);
        a.append("workSender", sender);
        a.append("workContent", content);
        a.append("workId", id);
        $.ajax({
            url: "/api/work/change",
            xhrFields: {
                withCredentials: true
            },
            type: "POST",
            cache: false,
            data: a,
            processData: false,
            contentType: false,
            async: false,
            success: function (result) {
                alert(result.data);
            },
            false: function (result) {
                alert(result.message);
            }
            //cache 上传文件不需要缓存，所以设置false
            //processData 因为data值是FormData对象，不需要对数据处理
            //contentType 因为是由form表单构造的FormData对象，且已声明了属性enctype，所以为false
        })
    }
}



//成员部分





//分页获取成员
function fetchmember(pageNum) {
    //分页获取成员接口
    let url1 = '/api/member/get?pageNum=' + pageNum;
    //获取成员总页数
    let url2 = '/api/member/pageNum';

    $.ajax(url2, {
        method: 'GET',
        success: (data) => {
            $('#pageSum').text("共" + data.data + "页");


        }

    });


    $.ajax(url1,
        {
            method: 'GET',
            success: function (data) {
                let html1 = '';
                for (i = 0; i < data.data.length; i++) {
                    let data1=data.data;
                    html1 +=
                        `
                   <tr class="text-c">
                    <td>${data1[i].memberId}</td>
                    <td><a href="javascript:;" ><i class="avatar size-L radius"><img alt="" src="${data1[i].memberPhoto}"></i></a></td>
                    <td>${data1[i].memberName}</td>
                    <td class="text-l"><div class="c-999 f-12">${data.data[i].memberPosition}</div></td>
                    <td class="f-14 td-manage"> <a style="text-decoration:none" class="ml-5" onclick="window.location='/ueditor/member.html?id='+${data1[i].memberId} "  title="修改"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onclick="confirmDeleteMember(${data1[i].memberId},${pageNum})"  title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                  </tr>
                `;
                    $('#memberTbody').html(html1);
                    $('#pageIndex').text("第" + pageNum + "页");

                }

            }
        })
    let html2=`<button onclick="memberPrePage()" class="btn btn-primary">上一页</button>
				<button onclick="memberNextPage()" style="margin-right: 600px;" class="btn btn-primary">下一页</button>
				<span id="pageIndex">第一页</span><span>/</span><span id="pageSum">第  页</span>`;
    $('.manageright_bottom_items').html(html2);
}



//成员分页
{

    let pageNum = 1;


    function memberPrePage() {
        let maxpageNum = $('#pageSum').text().match(/\d+/);

        pageNum--;
        if (pageNum < 1) {
            pageNum = 1;
        }
        ;
        fetchmember(pageNum);

    }

    function memberNextPage() {
        pageNum++;
        let maxpageNum = $('#pageSum').text().match(/\d+/);
        if (pageNum > maxpageNum) {
            pageNum = Number(maxpageNum);
        }
        ;
        fetchmember(pageNum);


    }

}




//成员删除


function memberdelete(id, e) {
    let url = '/api/member/updateState';
    $.ajax(url, {
        method: 'POST',
        data: {
            memberId: id,
        },
        success: (data) => {
        }
    });
    fetchwork(e);
}


//成员删除弹框

    function confirmDeleteMember(id, e) {
        if (confirm("确定删除该条新闻?")) {
            memberdelete(id, e)
        }
    }




//成员修改
function member_onload(id) {
    let url = '/api/member/selectById';
    $.ajax(url, {
        method: 'POST',
        data:{
            memberId:id,
        },
        success: (data) => {
            $("input[name='memberGrade']").val(data.data[0].memberGrade);
            $("input[name='memberPosition']").val(data.data[0].memberPosition);
            $("input[name='memberName']").val(data.data[0].memberName);
            $("input[name='pic']").val(data.data[0].memberPhoto);
            ue.setContent(data.data[0].memberAchievement);
            $('.bottomdiv').html(' <input type = "button"  value = "修改" onclick = "memberupdate(window.location.search.substr(window.location.search.lastIndexOf(\'=\')+1))" class="euploadBtn"/ >   <input type = "button" id = "submit3" value = "返回"  onClick = "window.location=\'/H-ui.admin/member-list.html\'" class="euploadBtn"/ >')
        }
    })
}


//成员修改按钮
function memberupdate(id) {

    let memberGrade = $("input[name='memberGrade']").val();
    let content = ue.getContent();
    let title = $("input[name='memberPosition']").val();
    let sender = $("input[name='memberName']").val();
    let pic = $("input[name='pic']").val();
    if (content === "" || content == null || content == undefined) {
        alert("请输入成员成就！");
        return false;
        /*阻止表单提交*/
    } else if (memberGrade == "" || memberGrade == null || memberGrade == undefined) {
        alert("请输入年级！");
        return false;
        /*阻止表单提交*/
    }
    else if (title == "" || title == null || title == undefined) {
        alert("请输入职位!");
        return false;
        /*阻止表单提交*/
    }else if (sender == "" || sender == null || sender == undefined) {
        alert("请输入成员姓名!");
        return false;
        /*阻止表单提交*/
    }


    else if (pic == "" || pic == null || pic === undefined) {
        alert("请上传照片！");
        return false;
        /*阻止表单提交*/
    }

    else {

        let a = new FormData();
        a.append("pic", $("#fileId")[0].files[0]);
        a.append("memberPosition", title);
        a.append("memberName", sender);
        a.append("memberAchievement", content);
        a.append('memberGrade',memberGrade);
        $.ajax({
            url: "/api/member/upload",
            xhrFields: {
                withCredentials: true
            },
            type: "POST",
            cache: false,
            data: a,
            processData: false,
            contentType: false,
            async: false,
            success: function (result) {
                alert(result.message);
            }
            //cache 上传文件不需要缓存，所以设置false
            //processData 因为data值是FormData对象，不需要对数据处理
            //contentType 因为是由form表单构造的FormData对象，且已声明了属性enctype，所以为false
        })
    }
}

//将日期转格式化
function getMoth(str) {
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        
        oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);
    return oTime;
}

function getzf(num) {
    if (parseInt(num) < 10) {
        num = '0' + num;
    }
    return num;
}
//将类型转化为汉字
function getArticleCategoryId(articleCategoryId){
    let a;
    if(articleCategoryId===1){
        a="教程";
    }else if(articleCategoryId===2){
        a="文章";
    }else if(articleCategoryId===3){
        a="观点";
    }else if(articleCategoryId===4){
        a="咨询";
    }else{
        a="作品";
    }
    return a;
}


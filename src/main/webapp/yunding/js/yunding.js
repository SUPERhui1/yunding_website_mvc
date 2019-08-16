
//加载新闻页面
function session1_onload(id) {
    let url = '/api/news/id?newsId=' + id;
    $.ajax(url, {
        method: 'GET',
        success: (data) => {
            let html5='';
            let data1 = data.data.data;
            html5 =
                `
                      <p class="article_title">${data1.newsTitle}</p>
                      <div class="article_author">
                          <img src="./pic/pic_admin.png">
                          <span style="margin-left: 10px">发布者：${data1.newsSender}</span>
                          <span style="margin-left: 40px;color:#288dbc">新闻</span>
                          <span style="margin-left: 35px">发布时间：${getMoth(data1.newsCreatedAt)}</span>
                      </div>
                      <div id="article_content" class="article_content">　${data1.newsContent}　</div>
        `;
            $('#article').html(html5);

        }

    })
}


//加载办公室图片
function session2_float(src) {
    $('#floatDiv').css('display', 'block');
    $('#fade').css('display', 'block');
    $('.black_overlay_back').css('display','block');
    let tops = $(document).scrollTop();
    $(document).bind("scroll", function () {
        $(document).scrollTop(tops);
    });

    html1 =
        `
     <img src="${src}"  class="floatDiv_pic" />
     `;
    $('#floatDiv').html(html1);
    $('#fade').css("background-image", "url(" + src + ")");
    $('#fade').css("background-size", "cover");

}

//加载文章页面


function session4_onload(id) {
    let url = '/api/article/getById';
    $.ajax(url, {
        data:{
            articleId:id,
        },
        method: 'GET',
        success: (data) => {
            let html5='';
            html5 =
                `
                      <p class="article_title">${data.data.articleIntroduce}</p>    
                      <div class="article_author">
                          <img src="./pic/pic_admin.png">
                          <span style="margin-left: 10px">发布者：${data.data.articleSender}</span>
                          <span style="margin-left: 40px;color:#288dbc">${getArticleCategoryId(data.data.articleCategoryId)}</span>
                          <span style="margin-left: 35px">发布时间：${getMoth(data.data.articleCreatedAt)}</span>
                      </div>
                      <div class="article_icons">
                         <i class="iconfont icon-view" style="font-size:25px;vertical-align:middle"></i>
                         <span style="margin-left: 10px">${data.data.articleViews}</span>
                         <i class="iconfont icon-like" style="margin-left: 30px"></i>
                         <span  style="margin-left: 10px">${data.data.articlePraise}</span>

                      </div>
                      <div id="article_content" class="article_content">　${data.data.articleContent}　</div>
                       　<div class="article_like_box">
                    <div class="article_hexagon_box1"></div>
                    <div class="article_hexagon_box2">
                    <i class="iconfont icon-xin"></i>
                    </div>
                    <div class="article_hexagon_box3"></div>
                    </div>　</div>
        `;
            $('#article').html(html5);

        }

    });
    viewsUp(id)
}

function viewsUp(id)
{
                $.ajax('/api/article/upViews',{
                method:'GET',
                data:{
                    articleId:id,
                },
                success:function (data){
                    console.log("浏览量加一");
                }
            })
}


function CloseDiv(show_div, bg_div) {
    document.getElementById(show_div).style.display = 'none';
    document.getElementById(bg_div).style.display = 'none';
    $(".black_overlay_back").css("display","none");
    $(document).unbind("scroll");
    $("#floatDiv2").animate({scrollTop: 0}, 1000);
};




//获取session1部分内容
function fetchSliders() {
    let url = '/api/news/selectPlaceNews';
    $.ajax(url, {
        method: "GET",
        success: (data) => {
            let html1 = '';
            for (let i = 0; i < data.data.length; i++) {
                html1 +=
                    ` 				 
              <div class="session1_slider_item"  >
                <a href="http://localhost:8080/yunding/news.html?id=${data.data[i].newsId}" >
                  <div class="session1_slider_item_pic">
                      <img src="${data.data[i].newsImage}" class="session1_slider_item_img">
                  </div>
                    
                  <div class="session1_slider_item_title">
                      <p class="session1_slider_item_nT">${data.data[i].newsTitle}</p>
                  </div>
                   </a>
              </div>

           
    `;
                $('.session1_slider').html(html1);
            }
            changeImg();

        }
    })
}

//获取session3部分内容
{
    function fetchsession3(id) {
        let url = '/api/inform/pageNum='+id;
        $.ajax(url, {
            method: "GET",
            success: (data) => {
                let html1 = '';
                for (let i = 0; i < data.data.data.length; i++) {

                    html1 +=
                        ` 				 
             	        			<p class="session3_words_p" style="display: none;">${data.data.data[i].informContent}</p>

                         `;

                    $('.session3_words').html(html1);
                }
                beginSession3();

            }
        })
    }
}
//获取session4内容
{

    let Num = 50;
    let pageNum1 = 1;
    let articleCategoryId1 = null;
    let hotOrTime1 = null;
    let PraiseNum=0;

    function getByPage1() {
        pageNum1--;
        if (pageNum1 < 1) {
            pageNum1 = 1;
        }

        UseSession4(pageNum1, articleCategoryId1, hotOrTime1);
    }

    function getByPage2() {
        pageNum1++;
        if (pageNum1 > Num) {
            pageNum1 = Num;
        }
        UseSession4(pageNum1, articleCategoryId1, hotOrTime1);
    }

    function getByHotOrTime(hotOrTime,e) {

        hotOrTime1 = hotOrTime;
        UseSession4(1, articleCategoryId1, hotOrTime);
        $('.session4_header_sortord_a').css("color","#b3b3b3");
        $('#'+e+' ').css("color","#288dbc");
    }

    function getByArticleCategoryId(articleCategoryId,e) {

        articleCategoryId1 = articleCategoryId;
        UseSession4(1, articleCategoryId, hotOrTime1);
        $('.session4_header_catalogue_a').css("color","#b3b3b3");
        $('#'+e+' ').css("color","#288dbc");
    }

    
    function praiseChange(e1,e2) {
        let num=Math.floor(Math.random()*5+1);
        let praisei = 'praisei' + e1 + '';
        let praiseicolor = $.trim($('#'+praisei+'').css("color"));
        let url="/api/article/changePraise";
        if (praiseicolor === 'rgb(0, 0, 0)'){



            $('#session4_content_article_box3_icon2_img'+e1).attr('src','./pic/like_'+num+'.png');
            $('#session4_content_article_box3_icon2_img'+e1).animate({
                height:'50px',
                width:'50px',
                bottom:"200px",
                opacity:"0",
                left:"10px",
            },3000);
            $('#' + praisei + '').css("color", "rgb(255,1,0)");

            $.ajax(url,{
                method:'GET',
                data:{
                    articleId:e2,
                    clickNum:1,
                },
                success:function (data) {
                    $("#praise"+e1).text(parseInt($("#praise"+e1).text())+1);
                    console.log("praise+1");
                },
            })
        };
        if (praiseicolor === 'rgb(255, 1, 0)'){
            $('#' + praisei + '').css("color", "rgb(0,0,0)");
            $('#session4_content_article_box3_icon2_img'+e1).attr('src','');
            $('#session4_content_article_box3_icon2_img'+e1).stop().animate({
                height:'50px',
                width:'50px',
                bottom:"200px",
                opacity:"0",
                left:"10px",
            },0);
            $('#session4_content_article_box3_icon2_img'+e1).animate({
                height:'16px',
                width:'16px',
                bottom:"20px",
                opacity:"1",
                left:"0",
            },0);
            $.ajax(url,{
                method:'GET',
                data:{
                    articleId:e2,
                    clickNum:2,
                },
                success:function (data) {
                    console.log("praise-1");
                    $("#praise"+e1).text(parseInt($("#praise"+e1).text())-1);
                    let url='/api/article/getById';
                },
            });
        }
    }

        function fetchSession4() {
            UseSession4(1,null,null);
            $('#session4_header_catalogue_a0 ').css("color","#288dbc");
            $('#session4_header_sortord_a1 ').css("color","#288dbc");
        }

        // ;
        // if (praiseicolor==='rgb(0,0,0)'){$('#'+praisei+'').css("color","255,1,0")};
        // PraiseNum++;
        // let url=/api/article/changePraise
        // $.ajax(url,{
        //
        // })


    function UseSession4(pageNum, articleCategoryId, hotOrTime) {
        let url = '/api/article/get';
        articleCategoryId1 = articleCategoryId;
        hotOrTime = hotOrTime1;
        $.ajax(url, {
            method: "POST",
            data: {
                pageNum: pageNum,
                articleCategoryId: articleCategoryId,
                hotOrTime: hotOrTime,
            },
            dataType: "json",
            success: function (data) {
                let html = '';
                for (let i = 0; i < data.data.length; i++) {
                    html +=
                        `    <div class="session4_content_article_boxes"> 
                       <a href="http://localhost:8080/yunding/article.html?id=${data.data[i].articleId}" >
						<div class="session4_content_article_box1" >
							<p>${getArticleCategoryId(data.data[i].articleCategoryId)}</p>
							<img src="${data.data[i].articleImage}" onclick="getById(${i},${data.data[i].articleId})" class="session4_content_article_box1_img">
						</div>
						<div class="session4_content_article_box2">
							<p class="session4_content_article_box2_p1">${data.data[i].articleIntroduce}</p>
							<p class="session4_content_article_box2_p2">${getMoth(data.data[i].articleUpdatedAt)}</p>

						</div>
						</a>
						<div class="session4_content_article_box3">
							<div class="session4_content_article_box3_admin">
								<img src="pic/pic_admin.png" alt="">
								<p >${data.data[i].articleSender}</p>
							</div>
							<div class="session4_content_article_box3_icons">
								<div class="session4_content_article_box3_icon1">
									<i  class="iconfont icon-view"></i>
									<p id="views${i}">${data.data[i].articleViews}</p>
								</div>
								<div onclick="praiseChange(${i},${data.data[i].articleId})" class="session4_content_article_box3_icon2" >
								    <img src="" alt="" class="session4_content_article_box3_icon2_img" id="session4_content_article_box3_icon2_img${i}">
									<i  class="iconfont icon-like" id="praisei${i}"></i>
									<p  id="praise${i}">${data.data[i].articlePraise}</p>
								</div>
							</div>
						</div>
						</div> 
        	          `
                }
                if (data.data.length < 6) {
                    for (let i = 0; i < 6 - data.data.length; i++) {
                        html += `
                                   <div class="session4_content_article_boxes"> 
                       <a href="http://localhost:8080/yunding/article.html?id=${data.data[i].articleId}" >
						<div class="session4_content_article_box1" >
							<p>${getArticleCategoryId(data.data[i].articleCategoryId)}</p>
							<img src="${data.data[i].articleImage}" onclick="getById(${i},${data.data[i].articleId})" class="session4_content_article_box1_img">
						</div>
						<div class="session4_content_article_box2">
							<p class="session4_content_article_box2_p1">${data.data[i].articleIntroduce}</p>
							<p class="session4_content_article_box2_p2">${getMoth(data.data[i].articleUpdatedAt)}</p>

						</div>
						</a>
						<div class="session4_content_article_box3">
							<div class="session4_content_article_box3_admin">
								<img src="pic/pic_admin.png" alt="">
								<p >${data.data[i].articleSender}</p>
							</div>
							<div class="session4_content_article_box3_icons">
								<div class="session4_content_article_box3_icon1">
									<i  class="iconfont icon-view"></i>
									<p id="views${i}">${data.data[i].articleViews}</p>
								</div>
								<div onclick="praiseChange(${i},${data.data[i].articleId})" class="session4_content_article_box3_icon2" id="1">
									  <img src="" alt="" class="session4_content_article_box3_icon2_img">
									<i  class="iconfont icon-like"></i>
									<p  id="praise${i}">${data.data[i].articlePraise}</p>
								</div>
							</div>
						</div>
						</div> 
                            `;

                    }
                }
                $('.session4_content').html(html);

            }
        });
    }

}


//获取session5部分内容

{
    let pagenum = 1;
    let num=10;
    function session5getbyPage2()
    {
        pagenum++;
        if(pagenum>num){
            pagenum=num;
        };
        fetchsession5(pagenum);
    }
    function session5getbyPage1() {
        pagenum--;
        if(pagenum<1){
            pagenum=1;
        };
        fetchsession5(pagenum);
    }

    function fetchsession5(pagenum) {
        if(pagenum==null){
            pagenum=1;
        };
        let url='/api/work/pageNum='+pagenum;
        $.ajax(url,{
            method:'GET',
             success:function(data){
                 let html1='';
                 let data1=data.data.data;
                 for (i=0;i<data1.length;i++) {
                     html1+=
                         `
                       	<div class="session5_content_boxes">
        	     		<img src="./pic/pic_cloud.png" class="session5_content_boxes_img1">
        	     		<p class="session5_content_boxes_title1">${data1[i].workName}</p>
        	     		<p class="session5_content_boxes_title2">${data1[i].workIntroduce}</p>
        	     		<img src="${data1[i].workImage}" class="session5_content_boxes_img2">

        	        	</div>
                         `;

                     $(".session5_content").html(html1);
                 }
        }
        })

    }
}




//获取session6部分内容

{

    function fetchsession6() {
        let url = '/api/member/selectGreatPerson';
        $.ajax(url,
            {
                method:'GET',
                success:function (data) {
                    let  html1='';
                    for (let i = 0; i <data.data.length ; i++) {
                        html1+=
                            `<div class="session6_content_boxes">
        				<img src="${data.data[i].memberPhoto}">
        				<p class="session6_content_boxes_title1">${data.data[i].memberName}</p>
        				<p class="session6_content_boxes_title2">${data.data[i].memberPosition}</p>
        			</div>`;
                        $(".session6_content").html(html1);
                    }


                }
            })
    }
}


//云顶院轮播
{

    function session7Show() {
        let show=$('.session7_bottom1').css("display");
        if($('.session7_bottom1').css("display")==='block'){
            $('.session7_bottom2').css("display","block");
            $('.session7_bottom1').css("display","none")
        }
        else{
            $('.session7_bottom1').css("display","block");
            $('.session7_bottom2').css("display","none")
        }


    }


}
//session1部分轮播图
{
    let index=0;

    function startAutoPlay() {
        timer=setInterval(function () {
            let sliders = $('.session1_slider_item');
            let count = sliders.length;
            index++;
            if(index>=count){
                index=0;
            }
            changeImg();
        },4000)
    }
  function changeImg() {
      let dots = $('.session1_dot');
      let sliders = $('.session1_slider_item');
      let count = sliders.length;
      for (let i = 0; i <count ; i++) {
          $(sliders.get(i)).fadeOut(1000);
          dots.css("background-color", "#b5ada6");
      }
      $(sliders.get(index)).fadeIn(1000);
      $(dots.get(index)).css("background-color", "#fff");

  }
  function slideImg() {
      let dots = $('.session1_dot');
      for (let i = 0; i < dots.length; i++) {

          dots.get(i).onclick=function () {
              index=i;
              changeImg();
          }
      }
      }

}

//session3部分轮播
function beginSession3() {
    let sliders = $('.session3_words_p');
    let count=sliders.length;
    let i=0;
    $(sliders.get(i)).fadeIn(1000);
    setInterval(function () {
        $(sliders.get(i)).fadeOut(1000);
        setTimeout(function () {
            i++;
            if(i===count){
                i=0;
            }
            $(sliders.get(i)).fadeIn(1000);
        },1000)
    },5000);
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

//网站加载

window.onload = function () {
    fetchSliders();
    fetchsession3(1);
    fetchSession4(1,null,null);
    fetchsession5();
    fetchsession6();
    startAutoPlay();
    slideImg();
};

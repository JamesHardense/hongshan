<#include "/classic/inc/layout.ftl"/>
<@layout "编辑词条">

<form id="submitForm" class="form" action="${base}/post/submit" method="post" enctype="multipart/form-data"
      xmlns="http://www.w3.org/1999/html">
    <input type="hidden" name="status" id="status" value="${view.status!0}"/>
    <input type="hidden" name="editor" id="editor" value="${editor!'tinymce'}"/>
    <div class="row">
        <div class="col-xs-12 col-md-8 side-left">
            <div id="message"></div>
<#--            <#if view??>-->
                <input type="hidden" name="id" id="id" value="${view.id}"/>
                <input type="hidden" name="authorId" id="authorId" value="${view.authorId}"/>
<#--            </#if>-->
            <div class="col-xs-12 col-md-12">
                <div class="form-group">
                    <div class="text-right">
                        <button type="button" data-status="0" class="btn btn-primary" event="post_submit" style="padding-left: 30px; padding-right: 30px;">发布</button>
                    </div>
                </div>
            </div>
            <h3>词条名称</h3>
            <input type="hidden" id="thumbnail" name="thumbnail" value="${view.thumbnail}"/>

            <div class="form-group">
                <input type="text" id="title"  class="form-control" name="title" maxlength="128" value="${view.title}" placeholder="请输入标题" required>
            </div>
<#--            <h3>编辑区</h3>-->

<#--            <h3>抽取百度百科词条</h3>-->
<#--            <div class="text-right">-->
<#--                <button type="button" data-status="0" class="btn btn-primary" style="padding-left: 30px; padding-right: 30px;">抽取</button>-->
<#--            </div>-->
            <br>


            <div class="form-group">
                <#include "/classic/channel/editor/${editor}.ftl"/>
            </div>
        </div>
        <div class="col-xs-12 col-md-4 side-right">
<#--            <div class="panel panel-default">-->
<#--                <div class="thumbnail-box">-->
<#--                    <div class="convent_choice" id="thumbnail_image"  <#if view.thumbnail?? && view.thumbnail?length gt 0> style="background: url(<@resource src=view.thumbnail/>);" </#if>>-->
<#--                        <div class="upload-btn">-->
<#--                            <label>-->
<#--                                <span>点击选择一张图片</span>-->
<#--                                <input id="upload_btn" type="file" name="file" accept="image/*" title="点击添加图片">-->
<#--                            </label>-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">分类</h3>
                </div>
                <div class="panel-body">
                    <select class="form-control" name="channelId" id="channelId" required>
                        <option value="">请选择分类</option>
                        <#list channels as row>
                            <option value="${row.id}" <#if (view.channelId == row.id)> selected </#if>>${row.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
<#--            <div class="panel panel-default">-->
<#--                <div class="panel-heading">-->
<#--                    <h3 class="panel-title">标签(用逗号或空格分隔)</h3>-->
<#--                </div>-->
<#--                <div class="panel-body">-->
<#--                    <input type="text" id="tags" name="tags" class="form-control" value="${view.tags}" placeholder="添加相关标签，逗号分隔 (最多4个)">-->
<#--                </div>-->
<#--            </div>-->
            <br>
            <div class="text-right">
                <#--                <button type="button" data-status="0" class="btn btn-primary" style="padding-left: 30px; padding-right: 30px;"-->
                <#--                        data-container="body" data-toggle="popover" data-placement="left"-->
                <#--                        data-content="左侧的 Popover 中的一些内容">抽取</button>-->
                <div class="text-center"  >
                    <h3  class="modal-title">百度词条抽取展示</h3>
                </div>
                <button type="button" id="popup" data-status="0" class="btn btn-primary" style="padding-left: 30px; padding-right: 30px;">抽取</button>

                <div id="div1"  style="display: none">
                    <div class="text-left">
                        <h5>简介</h5>
                        <span id="summary"></span>
                        <br>
<#--                        <#list BaiKe as row>-->
<#--                            <span>${row.summary}</span>-->
<#--                        </#list>-->
                        <br>
                        <h5>基本信息</h5>
                        <span id="basicInfo">
                        <!--存放数据-->
                        </span>
<#--                        <#list BaiKe as row>-->
<#--                            <span>${row.basicInfo}</span>-->
<#--                        </#list>-->
                        <br>
                    </div>
                </div>

            </div>
            <br>
            <br>
<#--            <div class="col-xs-12 col-md-12">-->
<#--                <div class="form-group">-->
<#--                    <div class="text-center">-->
<#--                        <button type="button" data-status="0" class="btn btn-primary" event="post_submit" style="padding-left: 30px; padding-right: 30px;">发布</button>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->

        </div>
    </div>
</form>
<!-- /form-actions -->
<script type="text/javascript">
// seajs.use('post', function (post) {
// 	post.init();
// }
// );
// $(function (){
//     $("[data-toggle='popover']").popover();
// });
$('button[event="post_submit"]').click(function () {
    if(document.getElementById("title").value===""){
        alert("请输入词条名称");
        document.getElementById("title").focus()
        return false;
    }
    if(document.getElementById("content").value===""){
        alert("请输入词条内容");
        document.getElementById("content").focus()
        return false;
    }
    if(document.getElementById("channelId").value===""){
        alert("请输入词条分类");
        document.getElementById("channelId").focus()
        return false;
    }
    // var status = $(this).data('status');
    // $("input[name='status']").val(status);
    // $("#submitForm").submit();
    if(document.getElementById("id").valueOf()===""){
        document.getElementById("id").value=0;
    }
    var  str ={
        title:document.getElementById("title").value,
        content:document.getElementById("content").value,
        channelId:document.getElementById("channelId").value,
        id:document.getElementById("id").value,
        authorId:document.getElementById("authorId").value,
        editor:document.getElementById("editor").value,
        status:document.getElementById("status").value};
    fetch(`http://localhost:9090/post/submit`,{
        method:'POST',
        body:JSON.stringify(str),
        headers:{'Content-Type':'application/json'}
    }).then(res=>{
        return res.text()
    }).then((res)=>{
        console.log(JSON.parse(res));
        if(res=="ok"){
            layer.confirm('等待管理员审核', {
                btn: ['确定'], //按钮
                shade: false //不显示遮罩
            }, function(){
                layer.closeAll();
                window.location.href = "http://localhost:9090/index"
            });
        }else {
            layer.confirm(res, {
                btn: ['确定'], //按钮
                shade: false //不显示遮罩
            }, function(){
                layer.closeAll();
                document.getElementById("title").focus();
            });
        }
    });
});
var J = jQuery;
    $('#popup').on('click', function(){
        var div = $("#div1").get(0);
        if(document.getElementById("title").value===""){
            alert("请输入词条名称");
            return false;
        }
        if(div.style.display == ""){
            div.style.display = "none";
        }else{
            div.style.display = "";
        }
        var data = fetch(`http://localhost:9090/post/baike`,{
            method:'POST',
            body:JSON.stringify({title:document.getElementById("title").value}),
            headers:{'Content-Type':'application/json'}}).then((res)=>{
            return res.text()
        }).then((res)=>{
            var response = JSON.parse(res);
            document.getElementById("summary").innerText = response.summary
            let basicInfoVOS = response.basicInfoVOS;
            let temp = '';
            for(let basicInfo of basicInfoVOS){
                temp +=
                    '<a>' + basicInfo.key + '</a>' +
                    '<a>' + ' : ' + '</a>' +
                    '<a>' + basicInfo.name+ '</a>' +
                    '<br>';
            }
            let tbody=document.getElementById("basicInfo");
            tbody.innerHTML = temp;


        });
});
</script>
</@layout>

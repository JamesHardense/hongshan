<#include "/classic/inc/layout.ftl"/>
<@layout "编辑词条">

<form id="submitForm" class="form" action="${base}/post/submit" method="post" enctype="multipart/form-data"
      xmlns="http://www.w3.org/1999/html">
    <input type="hidden" name="status" value="${view.status!0}"/>
    <input type="hidden" name="editor" value="${editor!'tinymce'}"/>
    <div class="row">
        <div class="col-xs-12 col-md-8 side-left">
            <div id="message"></div>
            <#if view??>
                <input type="hidden" name="id" value="${view.id}"/>
                <input type="hidden" name="authorId" value="${view.authorId}"/>
            </#if>
            <h3>词条名称</h3>
            <input type="hidden" id="thumbnail" name="thumbnail" value="${view.thumbnail}"/>

            <div class="form-group">
                <input type="text"  class="form-control" name="title" maxlength="128" value="${view.title}" placeholder="请输入标题" required>
            </div>
            <h3>编辑区</h3>
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
                    <select class="form-control" name="channelId" required>
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
                        <span></span>
                        <br>
                        <h5>基本信息</h5>
                        <span></span>
                        <br>
                    </div>
                </div>

            </div>
            <br>
            <br>
            <div class="col-xs-12 col-md-12">
                <div class="form-group">
                    <div class="text-center">
                        <button type="button" data-status="0" class="btn btn-primary" event="post_submit" style="padding-left: 30px; padding-right: 30px;">发布</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- /form-actions -->
<script type="text/javascript">
seajs.use('post', function (post) {
	post.init();
}
);
// $(function (){
//     $("[data-toggle='popover']").popover();
// });
var J = jQuery;
$(function () {
    $('#popup').on('click', function(){
        var div = $("#div1").get(0);
        if(div.style.display == ""){
            div.style.display = "none";
        }else{
            div.style.display = "";
        }
        J.getJSON('${base}/post/baike', J.param({'title': '计算机'}, true), function (result){

        });

    });
    // $('#qrcode').on('show.bs.modal', function (event) {
    //     var modal = $(this);  //get modal itself
    //     modal.find('')
    //     modal.find('.modal-body #message').text('your message');
    //     modal.find('.modal-body #scan').attr("src", 'image src');
    // });
});
</script>
</@layout>

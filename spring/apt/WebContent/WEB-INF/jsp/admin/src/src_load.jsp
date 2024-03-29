<%@page import="org.bouncycastle.asn1.x509.qualified.TypeOfBiometricData"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %>

<sp:sp var="RESULT" queryPath="ui" action="design" processorList="mybatis" exception="false"/>
<c:set var="use_set_code">
	unuse=미사용,
	use=기본,
	tree=Tree,
	chart_bar_iy=막대-(i.y),
	chart_bar_ixy=막대-(i.x.y),
	chart_bar_xy=막대-(x.y),
	chart_line_xy=Line-(x.y),
	chart_line_ixy=Line-(i.x.y),
	chart_pie_iy=Pie-(i.y),
	chart_bubble=Bubble-(i.x.y)
</c:set>
<c:set var="colType">
	[optgroup]=[[ 입력 ]],
	unuse=미사용,
	read=읽기전용,
	text=문자열,
	password=패스워드,
	textarea=문장,
	date=날짜,
	number=숫자,
	select=셀렉트박스,
	check=체크박스,
	radio=라디오박스,
	hidden=Hidden,
	file=첨부파일,
	files=첨부파일들,
	file_img=이미지파일,
	files_img=이미지파일들,
	button=버튼,
	del=삭제버튼,
	total_record=페이지 네비게이션,
	[/optgroup]=[[  ]],
	[optgroup]=[[ 조회 ]],
	text_view=[라벨],
	date_view=[날짜],
	datetime_view=[날짜시간],
	number_view=[숫자],
	select_view=[코드명],
	check_view=[코드명(체크)미구현],
	radio_view=[코드명(라디오)],
	group=[조회구룹],
	[/optgroup]=[[  ]],
	[optgroup]=[[ 트리 ]],
	upperFld=그룹,
	codeFld=코드,
	titleFld=코드명,
	idFld=키필드,
	[/optgroup]=[[  ]],
	[optgroup]=[[ 차트 ]],
	xFld=X값,
	yFld=Y값,
	zFld=Z값,
	lblFld=라벨
	[/optgroup]=[[  ]],
</c:set>
<c:set var="ui_design" value="${ui.UI_DESIGN }"/>
<c:set var="ui_field" value="${sp:str2jsonObj(ui.UI_FIELD) }"/>
<c:set var="query_path" value="${ui.query_path}"/>
<c:set var="query_path" value="${empty(query_path) ? param.queryPath : query_path}"/>
<input type="hidden" id="old_query_path" name="old_query_path" value="${query_path }">

<sp:sp var="UI_RESULT" queryPath="${fn:substringBefore(query_path,'.') }" action="${fn:substringAfter(query_path,'.' ) }" processorList="mybatis" exception="false">
	{
		${param.defaultValue }
	}
</sp:sp>

<c:set var="isInit" value="${!empty(ui_field.col_count)}"/>
<c:set var="col_count" value="${empty(ui_design) ? (empty(req.col_count) ? 4 : req.col_count) : ui_field.col_count}"/>
<table  class="lst">
	<tr>
		<th>타이틀</th><td><input type="text" id="ui_title" name="ui_title" value="${ui.ui_title }" style="width: 98%;"></td>
		<th>등록궈리</th><td><tag:select_query_name name="insert_path" selected="${ui['insert_path'] }"/></td>
		<th>사용버튼</th><td><tag:check_array name="use_btn" codes="L=닫기,C=취소,S=저장,U=수정,D=삭제"  checked="${ui.use_btn}" /></td>
	</tr><tr>
		<th>템플릿 패스</th><td><input type="text" id="tpl_path" name="tpl_path" value="${empty(ui.tpl_path) ? '-at-lo-pg' : ui.tpl_path }" style="width: 98%;"/></td>
		<th>수정궈리</th><td><tag:select_query_name name="update_path" selected="${ui.update_path }"/></td>
		<th>등록버튼Link</th><td>
			<tag:select_array name="add_type" codes="linkLoad=Load,linkPopup=팝업,linkPage=새페이지,linkFnc=함수"  selected="${ui.add_type}" />
			<input name="add_param" type="text" value="${ui.add_param }" style="width: 200px;;" title="<b>Load</b> : ui_id, {}, selector, <br><b>팝업</b> : ui_id, {}, <br><b>새페이지</b> : ui_id, {}, path, <br><b>함수</b> : function">
		</td>
	</tr><tr>
		<th>.</th><td></td>
		<th>삭제궈리</th><td><tag:select_query_name name="delete_path" selected="${ui.delete_path }"/></td>
		<th>저장CallBack</th><td>
			<tag:select_array name="calback_type" codes="linkLoad=Load,linkPopup=팝업,linkPage=새페이지,linkFnc=함수"  selected="${ui.calback_type}" />
			<input name="calback_param" type="text" value="${ui.calback_param }" style="width: 200px;;" title="<b>Load</b> : ui_id, {}, selector, <br><b>팝업</b> : ui_id, {}, <br><b>새페이지</b> : ui_id, {}, path, <br><b>함수</b> : function">
		</td>
	</tr>
</table>

<table id="resizable_container" class="ui_design_form" style="clear: both;width:100%; "><tr><td valign="top" width="*">
	<table id="ui_source_config" style="width:100% ;" class="lst" border="0" cellspacing="0" cellpadding="0" >
		<colgroup>
			<col width="100">
			<col width="100">
			<col width="100">
			<col width="300">
			<col width="*">
			<col width="180">
			<col width="40">
			<col width="20">
			<col width="20">
			<col width="40">
		</colgroup>
		<c:forEach var="map" items="${UI_RESULT }">
			
			<c:if test="${map.key!='success' && map.key!='JSON' }">
				<c:forEach var="temp" items="${map.value}">
					<c:set var="isList" value="${empty(temp['value']) ? 'Y' : '' }"/>
				</c:forEach>
				<tr>
					<td colspan="10" style="text-align: left; background: #D9E5FF;">
						<div  style="float:left; width: 50%; "><b>레코드 아이디</b> : ${map.key}</div>
						<c:set var="use_set" value="use_${map.key}"/>
						<div style="float: right;"><tag:radio_array name="${use_set}" codes="${use_set_code }"  checked="${empty(ui_field[use_set]) ? 'use' : ui_field[use_set] }" /></div>
						<div style="float: right;"><b>UI타입 :</b> &nbsp;</div>
					</td>
				</tr>
				<tr>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="2">필드</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="2">필드명</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="2">타입</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="2">링크</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="2">정합성</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="2">key필터</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="1" style="height:12px; padding-top: 0px;padding-bottom: 0px; font-size: 12px;">폭</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" colspan="2" style="height:12px; padding-top: 0px;padding-bottom: 0px; font-size: 12px;" title="리스트에서 항목 숨김">숨김</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" rowspan="2">검색</th>
				</tr>
				<tr>
					<th class="ui-state-default ui-th-column ui-th-ltr" style="height:14px; padding-top: 0px;padding-bottom: 0px;">
						<tag:check_array name="w_unit" codes="%=%"  checked="${ui_field['w_unit']}" />
					</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" style="height:14px; padding-top: 0px;padding-bottom: 0px;">폰</th>
					<th class="ui-state-default ui-th-column ui-th-ltr" style="height:14px; padding-top: 0px;padding-bottom: 0px;">웹</th>
				</tr>
				<tbody id="field_list" >
				<c:set var="fieldInfos" value="${sp:sortField(__META__UI_RESULT[map.key], ui_field['field_names_all']) }"/>
				<c:forEach var="info" items="${fieldInfos}">
				
					<tr class="field_settion" field_id="${info.key }">
						<c:set var="label">${info.key}_label</c:set>
						<c:set var="type">${info.key}_type</c:set>
						<c:set var="link">${info.key}_link</c:set>
						<c:set var="link_type">${info.key}_link_type</c:set>
						<c:set var="valid">${info.key}_valid</c:set>
						<c:set var="valid2">${info.key}_valid2</c:set>
						<c:set var="keyValid">${info.key}_key_valid</c:set>
						<c:set var="width">${info.key}_width</c:set>
						<c:set var="hide">${info.key}_hide</c:set>
						<c:set var="hide_web">${info.key}_hide_web</c:set>
						<c:set var="search">${info.key}_search</c:set>
						<c:set var="maxlength">${info.key}_maxlength</c:set>
						
						<c:set var="isInitField" value="${!empty(ui_field[label])}"/>
	
						<td class="label" label="${info.key}" title='${info }'>
							${info.key }
							<input type="hidden" name="field_names"   value="${info.key}">
							<input type="hidden" name="${maxlength}"   value="${info.value.precision}">
						</td>
						<td><!-- 필드명 -->
							<c:set var="label_lang"><src:lang id="${info.key }"/></c:set>
							<c:set var="label_val">${ui_field[label] }</c:set>
							
							<div class="field_name${isList } drg${isList } th${isList }" type="field_name" title="${info.key }" style="border${isList }: 1px solid #c5dbec; height: 20px;cursor${isList }: move; display: inline;">
								${isList=='Y' ? '' : '☺' }<input class="field_label" type="text" name="${label}" style="width: 70px;" value="${(empty(label_val) || info.key==label_val) ? label_lang : label_val }">
							</div>
						</td>
						<td title="Tree의 그룹타입으로 지정한 경우 필드명에 최상위 아이디를 넣으세요."><!-- 필드타입 -->
							<c:set var="fieldType" value="${ui_field[type] }"/>
							<c:if test="${!isInitField}">
								<!-- 필드속성을 자동으로 초기화 한다. -->
								<c:set var="fieldType">
									<c:choose>
										<c:when test="${isList=='Y'}">
											<c:choose>
												<c:when test="${fn:endsWith(info.key, 'file_group_id')}">file</c:when>
												<c:when test="${info.value.type == 'INTEGER' || info.value.type == 'BIGINT'}">number_view</c:when>
												<c:when test="${info.value.type == 'TIMESTAMP'}">date_view</c:when>
												<c:otherwise>label</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${fn:endsWith(info.key, 'file_group_id')}">file</c:when>
												<c:when test="${info.value.type == 'INTEGER' || info.value.type == 'BIGINT'}">number</c:when>
												<c:when test="${info.value.type == 'TIMESTAMP'}">date</c:when>
												<c:when test="${info.value.precision > 255}">textarea</c:when>
												<c:otherwise>text</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:set>
							</c:if>
							
							<c:set var="tmpFieldType">$(info.key)</c:set>
							
							<div class="field${isList } drg${isList }" type="field"  title="${info.key }" style="border${isList }: 1px solid #c5dbec; height: 20px;cursor${isList }: move;  display: inline;" >
								${isList=='Y' ? '' : '☺' }<tag:select_array codes="${colType }" name="${type }" selected="${fieldType }" style="width: 90px;" attr=" title='${info.value.type }'"/>
							</div>
						</td>
						<td>
							<tag:select_array  name="${link_type}" codes="linkLoad=Load,linkPopup=팝업,linkPage=새페이지,linkFnc=함수" style=" "  selected="${ui_field[link_type]}" />
							<input class="input_link" name="${link}" type="text" value="${ui_field[link] }" style="width: 200px;vertical-align: top;" title="<b>Load</b> : ui_id, {}, selector or 숫자, <br><b>팝업</b> : ui_id, {}, <br><b>새페이지</b> : ui_id, {}, path, <br><b>함수</b> : function">
						</td>
						<td>
							<c:set var="valids">${valid}[]</c:set>
							<c:set var="validValue">notempty${fieldType=='date' ? ',date' : '' }</c:set>
							<tag:check_array name="${valid}" codes="notempty=필수입력,date=날짜,rangedate=기간날짜,ext:jpg:jpeg:png:gif=업로드 ext"  checked="${isInitField ? ui_field[valids] : validValue}" />
							<input type="text" name="${valid2}" style="width: 200px;;" value="${ui_field[valid2] }">
						</td>
						<td>
							<tag:radio_array name="${keyValid}" codes="alpa=영문,numeric=숫자,alpa_numeric=영숫자"  checked="${isInitField ? ui_field[keyValid] : (fieldType=='number' ? 'numeric' : '') }" />
						</td>
						<td><input type="text" name="${width}" style="width: 100%;" value="${isInitField ? ui_field[width] : 10 }"></td>
						<td align="center">
							<tag:check_array name="${hide}" codes="hide= "  checked="${isInitField ? ui_field[hide] : false}" />
						</td>
						<td align="center">
							<tag:check_array name="${hide_web}" codes="hide_web= "  checked="${isInitField ? ui_field[hide_web] : false}" />
						</td>
						<td align="center">
							<tag:check_array name="${search}" codes="search= "  checked="${isInitField ? ui_field[search] : false}" />
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</c:if>
		</c:forEach>
	</table>

</td><td valign="top"  style="display: none; ">

	<div style="text-align:left;  ; background: #cccccc;">
		<b>UI 디자인</b><br>☺아이콘을 드래그하여 배치하세요.
		<br>빈 공간은 삭제되니 콘트롤 사이즈를 조절하거나 아래 아이콘을 끌어 놓으세요.
		<div class="to_field_td to_field" style="width: 100%;"><div class="to_field" style="height: 20px; margin: 0px ;border: 1px solid #c5dbec; background: #ffffff;">
			<c:forEach begin="1" end="5" step="1">
				<span class="field drg" type="field"  title="empty" style="border: 1px solid #c5dbec; height: 20px; cursor: move;">☺&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</c:forEach>
		</div></div>
	</div>
	<div id="ui_source_dgn">
		${ui_design}
		<c:if test="${empty(ui_design) }">
			<table class="tpl" >
				<c:forEach begin="1" end="10" step="1">
					<tr>
						<c:forEach begin="1" end="${col_count }" step="1">
							<td class="to_field_td" style="width: 100px;background: #eeeeee;"><div class="to_field" style="height: 20px; margin: 0px ;"></div></td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</td></table>
<%@ page import="edu.ntu.eee.csn.ism.egene.util.TplmDecimals,java.util.List" %>
<%@ include file="header.jsp"%>

<script type="text/javascript">

	$(function() {
		var $elem = $('.content');
		
		$('#nav_up').fadeIn('slow');
		$('#nav_down').fadeIn('slow');  
		
		$(window).bind('scrollstart', function(){
			$('#nav_up,#nav_down').stop().animate({'opacity':'0.2'});
		});
		$(window).bind('scrollstop', function(){
			$('#nav_up,#nav_down').stop().animate({'opacity':'1'});
		});
		
		$('#nav_down').click(
			function (e) {
				$('html, body').animate({scrollTop: $elem.height()}, 800);
			}
		);
		$('#nav_up').click(
			function (e) {
				$('html, body').animate({scrollTop: '0px'}, 800);
			}
		);
	});
			
			
	function changePage(currPage, totalPage) {		
		
		var start = 1;
		var end = totalPage;

		if ((totalPage-currPage)<2) {
			end = totalPage;
			start = end - 4;
			if (start < 1) {
				start = 1;
			}
		} else if (totalPage > 5) {
			if ((currPage-2) > 0) {
				start = currPage-2;
			}
			end = start + 4;	
		}
		
		for (j = 1; j <= totalPage; j++) {
		
			if (j != currPage) {
				$("#paget" + j).removeClass("page_selected").addClass("page_unselected");
				$("#pageb" + j).removeClass("page_selected").addClass("page_unselected");
				$("#ep_page" + j).hide();
			} else {
				$("#paget" + j).removeClass("page_unselected").addClass("page_selected");
				$("#pageb" + j).removeClass("page_unselected").addClass("page_selected");
				$("#ep_page" + j).show();
			}		

			if ((j >= start) && (j <= end)) {
				$("#paget" + j).show();
				$("#pageb" + j).show();
			} else {
				$("#paget" + j).hide();
				$("#pageb" + j).hide();
			}
			
		}
		
		//alert($(".ep_solution_step").css("display"));
		if ((currPage == totalPage) && ($(".ep_solution_step").css("display") == "none")) {
			$("#eps_command_bottom").show();
		} else {
			$("#eps_command_bottom").hide();
		}
					
		return false;
	}
	
	
	function showSolution() {
		$(".e_exam_paper").show();
		$(".ep_answer_step").hide();
		$(".ep_answer_final").hide();
		$(".ep_solution_step").show();
		$("#eps_command_bottom").hide();
		$(".e_grading").hide();		
		return false;
	}
	
	
	function gradePaper() {
		$(".e_engine").hide();
		$(".e_exam_paper").hide();
		$(".e_grading").show();
		return false;
	}
	
			
	function generateQuestions(typeCount) {
		
		var qtypes = "";
		var idx = 0;
		var epCount = 0;
		for (idx=0; idx < typeCount; idx++) {
			
			epCount += parseInt($("#qtype_select" + idx + " option:selected").text());
			qtypes += $("#qtype_select" + idx).val();
			if (idx != (typeCount-1)) {
				qtypes += "|";
			}
		}
		
		if (epCount == 0)
			return false;
		
		var qpage_len = $("#qpage_len").val();
		$.post(
			"SvGenerateExam", 
			{qtypes : qtypes},
			function(xml) {
			
				// print exam paper
				$("#eps_body").html("");
				var i = 0;
				var p = 0;
				var str = "";
				$(xml).find("ep").each(function() {
				
					if ((i % qpage_len == 0) && (str != "")) {
						p++;
					
						$("#eps_body").append("<div class='ep_page' id='ep_page" + p + "'>" + str + "</div>");
						str = "";

					}
					i++;
				
					var question = $(this).find("question").text();
					var answer = $(this).find("answer").text();
					str += "<div class='ep_num' id='ep_num" + i + "'>";
					str += "<div class='ep_question' id='ep_question" + i + "'><h3>Question " + i + ":</h3><p>" + question + "</p></div>";
					str += "<div class='ep_answer_step' id='ep_answer_step" + i + "'><span>Space for working:</span><br /><textarea rows='5' cols='80'></textarea></div>";
					str += "<div class='ep_answer_final' id='ep_answer_final" + i + "'><span>Final answer:</span><br /><input type='text' size='50'/></div>";
					str += "<div class='ep_solution_step' id='ep_solution_step" + i + "'><span>Solution:</span><br />" + answer + "</div>";
					str += "<div class ='ep_solution_final' id='ep_solution_final" + i + "'>0</div>";
					str += "</div>";
				});
				
				if (str != "") {
					$("#eps_body").append("<div class='ep_page' id='ep_page"+(++p)+"'>" + str + "</div>");
					str = "";
				}
				
				
				// setup paging bottom
				if (p > 1) {
					$("#eps_page_navigator_top").show();
					$("#eps_page_navigator_bottom").show();
				
					var j = 0;

					var strPageb = "";
					strPageb += "<a class='page_unselected' id='pageb_first' title='page 1' href='#' onClick='"+ "changePage(1, " + p + ")" +"'>&lt;&lt;</a>";

					var strPaget = "";
					strPaget += "<a class='page_unselected' id='paget_first' title='page 1' href='#' onClick='"+ "changePage(1, " + p + ")" +"'>&lt;&lt;</a>";

					for (j = 1; j <= p; j++) {					
						if (j != 1) {
							strPaget += "<a class='page_unselected' id='paget"+ j +"' title='page " + j + "' href='#' onClick='"+ "changePage("+ j + ", " + p + ")" +"'>" + j + "</a>";
							strPageb += "<a class='page_unselected' id='pageb"+ j +"' title='page " + j + "' href='#' onClick='"+ "changePage("+ j + ", " + p + ")" +"'>" + j + "</a>";
							$("#ep_page" + j).hide();
						} else {
							strPaget += "<a class='page_selected' id='paget" + j + "' title='page " + j + "' href='#' onClick='"+ "changePage(" + j + ", " + p + ")" + "'>" + j + "</a>";
							strPageb += "<a class='page_selected' id='pageb" + j + "' title='page " + j + "' href='#' onClick='"+ "changePage(" + j + ", " + p + ")" + "'>" + j + "</a>";
						}						
					}
					
					strPaget += "<a class='page_unselected' id='paget_last' title='page "+ p + "' href='#' onClick='"+ "changePage(" + p + ", " + p + ")" + "'>&gt;&gt;</a>";
					$("#eps_page_navigator_top").html(strPaget);

					strPageb += "<a class='page_unselected' id='pageb_last' title='page "+ p + "' href='#' onClick='"+ "changePage(" + p + ", " + p + ")" + "'>&gt;&gt;</a>";
					$("#eps_page_navigator_bottom").html(strPageb);
					
					for (j = 1; j<=p; j++) {
						if (j >= 1 && j <= 5) {
							$("#paget" + j).show();
							$("#pageb" + j).show();
						} else {
							$("#paget" + j).hide();
							$("#pageb" + j).hide();
						}
					}
				} else {
						$("#eps_page_navigator_top").hide();
						$("#eps_page_navigator_bottom").hide();
						$("#eps_command_bottom").show();
				}					
				
				// print title
				$("#eps_title").html("<h2>Exam Paper</h2><h4>Topic: Decimals</h4><h4>Generated on: " + showDate() + "</h4>");
				
				
				// hide main menu
				$(".e_engine").hide();
				$(".e_exam_paper").show();				

				
				// automatically scroll up
				$('html, body').animate({scrollTop: '0px'}, 800);
				
			}
		);
		

		
		return false;
	}

	$(document).ready(function() {
		$(".table_qtypes tr").mouseover(function(){$(this).addClass("tblover");}).mouseout(function(){$(this).removeClass("tblover");});

		/*$(".ep_num").hover(
			function(){
				alert("enter");
			},
			function(){
				alert("leave");
			}
		);*/
	});
	
	
</script>

<div class="content">
	<div class="content_resize">
	
		<div class="e_engine">
			<div class="title"><h2>Exam Paper Generator Engine for Topic: Decimals</h2>
			</div>
			<div class="command_top"><p>command_top</p>
			</div>
			<div class="page_nav_top"><p>page_nav_top</p>
			</div>
			<div class="article">
				<form>
					<table class="table_qtypes">
						<thead>
							<tr>
								<th class="qtype">Question Types:</th>
								<th class="qnote">&nbsp;</th>
								<th class="qtype">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<%
								List<TplmDecimals> list = TplmDecimals.getTplmDecimalsList();
								for(int i = 0; i < list.size(); i++) {
									out.println("<tr>");
									out.println("<td class='qtype'>" + list.get(i).getQuestionType() + "</td>");
									out.println("<td class='qtype_note'>number of questions to be generated:</td>");
									out.println("<td class='qtype_select'><select id='qtype_select" + i + "' name='qtype_select" + i + "'>");
									out.println("<option selected='true' value='" + list.get(i).getId() + ";0'>0</option>");
									out.println("<option value='" + list.get(i).getId() + ";5'>5</option>");
									out.println("<option value='" + list.get(i).getId() + ";10'>10</option>");
									out.println("<option value='" + list.get(i).getId() + ";20'>20</option>");
									out.println("<option value='" + list.get(i).getId() + ";50'>50</option>");
									out.println("</select></td>");
									out.println("</tr>");
								}								
							%>
						</tbody>
					</table>
					
					<p>
						Number of questions to be generated per page: 
						<select id="qpage_len" name="qpage_len">
							<option value="5" selected="true">5</option>
							<option value="10">10</option>
							<option value="20">20</option>
						</select>
					</p>
					
				</form>
			</div>
			<div class="page_nav_bottom"><p>page_nav_bottom</p>
			</div>
			<div class="command_bottom">
				<ul><li>
					<%
						out.println("<a href='#' onClick='return generateQuestions(" + list.size() + ");'>Generate Questions</a>");
					%>
				</li></ul>
			</div>			
		</div>
		
		<div class="e_exam_paper">
			<div class="title" id="eps_title">
			</div>
			<div class="command_top">
				<ul>
					<li><span>Convert all Questions to Word Document</span></li>
					<li><a href="" onClick="return false;">Without Solutions</a></li>
					<li><a href="" onClick="return false;">With Solutions</a></li>
				</ul>
			</div>
			<div class="page_nav_top" id='eps_page_navigator_top'>
			</div>
			<div class="article" id="eps_body">
			</div>
			<div class="page_nav_bottom" id='eps_page_navigator_bottom'>
			</div>
			<div class="command_bottom" id="eps_command_bottom">
				<ul>
					<li><a href="#" onClick="return showSolution();">Generate Solutions</a></li>
					<li><a href="#" onClick="return gradePaper();">Grade Paper</a></li>
				</ul>
			</div>			
		</div>

		<div class="e_grading">
			<div class="title"><p>title</p>
			</div>
			<div class="command_top"><p>command_top</p>
			</div>
			<div class="page_nav_top"><p>page_nav_top</p>
			</div>
			<div class="article"><p>article</p>
			</div>
			<div class="page_nav_bottom"><p>page_nav_bottom</p>
			</div>
			<div class="command_bottom"><p>command_bottom</p>
				<ul>
					<li><a href="#" onClick="return showSolution();">Generate Solutions</a></li>
				</ul>
			</div>	
		</div
		
		<div class="clr"></div>
	</div>
</div>



<%@ include file="footer.jsp"%>
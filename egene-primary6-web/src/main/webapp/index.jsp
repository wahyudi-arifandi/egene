<%@ include file="header.jsp"%>

<script type="text/javascript">


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
		
		for ($j = 1; $j <= totalPage; $j++) {
		
			if ($j != currPage) {
				$("#ep_num_" + $j).hide();
			} else {
				$("#ep_num_" + $j).show();
			}
			
			if ($j != currPage) {
				$("#pn_" + $j).removeClass("pselected").addClass("punselected");
				$("#pn2_" + $j).removeClass("pselected").addClass("punselected");
				$("#ep_num_" + $j).hide();
			} else {
				$("#pn_" + $j).removeClass("punselected").addClass("pselected");
				$("#pn2_" + $j).removeClass("punselected").addClass("pselected");
			}		

			if (($j >= start) && ($j <= end)) {
				$("#pn_" + $j).show();
				$("#pn2_" + $j).show();
			} else {
				$("#pn_" + $j).hide();
				$("#pn2_" + $j).hide();
			}
			
		}
					
		return false;
	}

	function showHideAnswer(id) {
	
		if ($(id).css("display") == "none") {
			$(id).show();
		} else {
			$(id).hide();
		}
		return false;
	}


	$(document).ready(function() {

		$(".mainbar_tools").hide();
		$(".mainbar2_tools").hide();
		$(".eps_gendate").hide();
		$(".eps_pages").hide();
		$("#epCount").numeric({ decimal: false, negative: false }, function() { alert("Positive integers only"); this.value = ""; this.focus(); });	
		$("#epCountPage").numeric({ decimal: false, negative: false }, function() { alert("Positive integers only"); this.value = ""; this.focus(); });	

		$("#qtopic").change(function () {
			if($(this).val() == "default") {
				$(this).addClass("qtopic_empty");
				$("#epCount").attr('disabled', true);
				$("#epCountPage").attr('disabled', true);
				$("#epGen").attr('disabled', true);
				$("#epGen").attr('src', 'images/submit-disable.gif');
			} else {
				$(this).removeClass("qtopic_empty")
				$("#epCount").attr('disabled', false);
				$("#epCountPage").attr('disabled', false);
				$("#epGen").attr('disabled', false);
				$("#epGen").attr('src', 'images/submit.gif');
			}
		});
		$("#qtopic").change();		
		
		$("#epGen")
			.mouseover(function() { 
				var src = "images/submit-onover.gif";
				$(this).attr("src", src);
			})
			.mousedown(function() { 
				var src = "images/submit-onpress.gif";
				$(this).attr("src", src);
			})
			.mouseout(function() {
				var src = "images/submit.gif";
				$(this).attr("src", src);
			});
		
		$("#epGen").click(function() {
		
			if( $("#qtopic").val() == "default") {
				return false;
			}

			$(".mainbar_tools").show();
			$(".mainbar2_tools").show();
			$(".eps_gendate").show();
			
			$epCount = $("#epCount").val();
			$qtype = $("#qtopic").val();
			$epCountPage = $("#epCountPage").val();
			$.post(
				"SvGenerateExam", 
				{epcount : $epCount, qtype : $qtype},
				function(xml) {
				
					$("#mainbar_tools").html(
						"&nbsp;<a href='#' onclick='printPage()' class='print_icon'><span>print without answer</span></a>"
					);

					$("#mainbar2_tools").html(
						"&nbsp;<a href='#' onclick='printPageNoCSS()' class='print_icon'><span>print with answer</span></a>"
					);
					
					$("#eps_gendate").html(
						"&nbsp;<span>Generated on " + showDate() + "</span>");
						
					$("#eps_gentopic").html(
						"<h2>Exam Paper: " + $("#qtopic option:selected").text() +"</h2>"
					);
				
				
					$("#eps").html("");
					var $i = 0;
					var $p = 0;
					var $str = "";
					$(xml).find("ep").each(function() {
					
						if (($i % $epCountPage == 0) && ($str != "")) {
							$p++;
						
							$("#eps").append("<div id='ep_num_" + ($p) + "'>" + $str + "</div>");
							$str = "";

						}
						$i++;
					
						$question = $(this).find("question").text();
						$answer = $(this).find("answer").text();
						$str += "<div><br /><strong>#" + $i + "# Question:</strong><br />" + $question + "</div>";
						$str += "<div><a href='#' onclick=\"return showHideAnswer('#epanswer_" + $i + "');\"><img src='images/icon-show-hide.png'></a></div>";
						$str += "<div class='epanswer' id='epanswer_" + $i + "'><strong>Answer:</strong><br />" + $answer + "</div>";
						
					});
					
					if ($str != "") {
						$("#eps").append("<div id='ep_num_"+(++$p)+"'>" + $str + "</div>");
					}
					
					if ($p > 1) {
						$(".eps_pages").show();
					
						var $j = 0;
						var $strP = "";
						$strP += "<a class='punselected' id='pn_first' title='page 1' href='#' onClick='"+ "changePage(1, "+$p+")" +"'>&lt;&lt;</a>"
						for ($j = 1; $j <= $p; $j++) {					
							if ($j != 1) {
								$strP += "<a class='punselected' id='pn_"+$j+"' href='#' onClick='"+ "changePage("+$j+", "+$p+")" +"'>"+$j+"</a>"
								$("#ep_num_" + $j).hide();
							} else {
								$strP += "<a class='pselected' id='pn_"+$j+"' href='#' onClick='"+ "changePage("+$j+", "+$p+")" +"'>"+$j+"</a>"
							}
							
						}
						$strP += "<a class='punselected' id='pn_last' title='page "+$p+"' href='#' onClick='"+ "changePage("+$p+", "+$p+")" +"'>&gt;&gt;</a>";
						$("#pages1").html($strP);
						
						for ($j = 1; $j<=$p; $j++) {
							if ($j >= 1 && $j <= 5) {
								$("#pn_" + $j).show();
							} else {
								$("#pn_" + $j).hide();
							}
						}
						
						var $k = 0;
						var $strP2 = "";
						$strP2 += "<a class='punselected' id='pn2_first' title='page 1' href='#' onClick='"+ "changePage(1, "+$p+")" +"'>&lt;&lt;</a>"
						for ($k = 1; $k <= $p; $k++) {					
							if ($k != 1) {
								$strP2 += "<a class='punselected' id='pn2_"+$k+"' href='#' onClick='"+ "changePage("+$k+", "+$p+")" +"'>"+$k+"</a>"
								$("#ep_num_" + $k).hide();
							} else {
								$strP2 += "<a class='pselected' id='pn2_"+$k+"' href='#' onClick='"+ "changePage("+$k+", "+$p+")" +"'>"+$k+"</a>"
							}
							
						}
						$strP2 += "<a class='punselected' id='pn2_last' title='page "+$p+"' href='#' onClick='"+ "changePage("+$p+", "+$p+")" +"'>&gt;&gt;</a>";
						$("#pages2").html($strP2);
						
						for ($k = 1; $k<=$p; $k++) {
							if ($k >= 1 && $k <= 5) {
								$("#pn2_" + $k).show();
							} else {
								$("#pn2_" + $k).hide();
							}
						}						
					} else {
						$(".eps_pages").hide();
					}
					
				}
			);
			return false;
		});
	});
	
	
	
</script>

<div class="content">
	<div class="content_resize">
		<div class="mainbar">

			<div id="mainbar_tools" class="mainbar_tools">
			</div>
			<div id="mainbar2_tools" class="mainbar_tools">
			</div>

			<div class="printable">
			
				<div id="eps_gendate" class="eps_gendate">
				</div>

				<div class="eps_pages">&nbsp;
					<p class="pages" id="pages1">&nbsp;
					</p>
				</div>

				<div id="eps_gentopic" class="eps_gentopic">
					<h2>Welcome to eGENE</h2>
				</div>
				
				<div id="eps" class="eps">
					<p>eGENE is an automatic exam paper generator. Currently it supports exam peper generation limited to topic Decimals on Mathematics Primary 6, based on Singapore syllabus.</p>
					<p>eGENE generates a collection of exam paper randomly based on predefined templates.</p>
					<p>Please do following steps:<br />
						1. Select the topic of exam paper to be generated.<br />
						2. Select the number of questions to be generated. Allowed value is 1 t0 999.<br />
						3. Select the number of questions to be displayed per page as the result. Allowed value is 1 to 99.<br />
					</p>
					<p>To modify existing templates or add new templates into eGENE, please go to Configurations menu</p>
				</div>
				
				<div class="eps_pages">&nbsp;
					<p class="pages" id="pages2">&nbsp;
					</p>
				</div>				
				
			</div>

		</div>


		<div class="sidebar">


			<div class="searchform">
				<form id="formsearch" name="formsearch" method="post"
					action="search.jsp">
					<span><input name="editbox_search" class="editbox_search"
						id="editbox_search" maxlength="80" placeholder="Search our site:"
						type="text" />
					</span> <input name="button_search" src="images/search_btn.gif"
						class="button_search" type="image" />
				</form>
			</div>
			<div class="gadget">
				<h2 class="star">
					<span>Generator Engine</span>
				</h2>
				<div class="clr"></div>
				<form action="#" method="post" id="sendemail">
					<ol>
						<li><label for="qtopic">Question Type</label> <select id="qtopic"
							name="qtopic">
								<option id="default" value="default" selected>-- select --</option>
								<option id="review" value="review">Review</option>
								<!-- 
								<option id="psp21.1.vm" value="psp21.1.vm">psp21.1</option>
								<option id="psp22.1.vm" value="psp22.1.vm">psp22.1</option>
								<option id="psp22.2.vm" value="psp22.2.vm">psp22.2</option>
								<option id="psp22.3.vm" value="psp22.3.vm">psp22.3</option>
								<option id="psp23.1.vm" value="psp23.1.vm">psp23.1</option>
								<option id="psp23.2.vm" value="psp23.2.vm">psp23.2</option>
								<option id="psp23.3.vm" value="psp23.3.vm">psp23.3</option>
								<option id="psp23.4.vm" value="psp23.4.vm">psp23.4</option>
								<option id="psp24.1.vm" value="psp24.1.vm">psp24.1</option>
								<option id="psp25.1.vm" value="psp25.1.vm">psp25.1</option>
								<option id="psp25.2.vm" value="psp25.2.vm">psp25.2</option>
								<option id="psp25.3.vm" value="psp25.3.vm">psp25.3</option>
								 -->
								<option id="psp26.1.vm" value="psp26.1.vm">psp26.1</option>
								<option id="psp26.2.vm" value="psp26.2.vm">psp26.2</option>
								<option id="psp27.1.vm" value="psp27.1.vm">psp27.1</option>
								<option id="psp27.2.vm" value="psp27.2.vm">psp27.2</option>
								<option id="psp28.1.vm" value="psp28.1.vm">psp28.1</option>
								<option id="psp29.1.vm" value="psp29.1.vm">psp29.1</option>
						</select></li>
						<li><label for="epCount">number of questions to be
								generated</label> <input id="epCount" name="epCount" maxlength="3"
							size="2" value="10" /></li>
						<li><label for="epCountPage">questions per page</label> <input
							id="epCountPage" name="epCountPage" maxlength="2" size="2" value="10" /></li>
						<li><input type="image" name="epGen" id="epGen"
							src="images/submit.gif" class="send" />
							<div class="clr"></div></li>
					</ol>
				</form>
				
			</div>
		</div>

		<div class="clr"></div>
	</div>
</div>


<%@ include file="footer.jsp"%>
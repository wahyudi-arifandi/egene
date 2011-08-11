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
				$("#ep_num_" + $j).hide();
			} else {
				$("#pn_" + $j).removeClass("punselected").addClass("pselected");
			}		

			if (($j >= start) && ($j <= end)) {
				$("#pn_" + $j).show();
			} else {
				$("#pn_" + $j).hide();
			}
			
		}
					
		return false;
	}

	$(document).ready(function() {

		$(".mainbar_tools").hide();
		$(".eps_gendate").hide();
		$(".eps_pages").hide();
		$("#epCount").numeric({ decimal: false, negative: false }, function() { alert("Positive integers only"); this.value = ""; this.focus(); });	
		$("#epCountPage").numeric({ decimal: false, negative: false }, function() { alert("Positive integers only"); this.value = ""; this.focus(); });	

		$("#epGen").click(function() {
		
			if( $("#qtopic").val() == "default") {
				return false;
			}

			$(".mainbar_tools").show();
			$(".eps_gendate").show();
			
			$epCount = $("#epCount").val();
			$tableName = $("#qtopic").val();
			$epCountPage = $("#epCountPage").val();
			$.post(
				"SvGenerateExam", 
				{epcount : $epCount, tablename : $tableName},
				function(xml) {
				
					$("#mainbar_tools").html(
						"&nbsp;<a href='#' onclick='printPage()' class='print_icon'><span>print</span></a>"
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
						$str += "<p><strong>#" + $i + "# Question:</strong><br />" + $question + "<br /><strong>Answer:</strong><br />" + $answer + "</p>";
						
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
						$("#pages").html($strP);
						
						for ($j = 1; $j<=$p; $j++) {
							if ($j >= 1 && $j <= 5) {
								$("#pn_" + $j).show();
							} else {
								$("#pn_" + $j).hide();
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

			<div class="printable">
			
				<div id="eps_gendate" class="eps_gendate">
				</div>

				<div class="eps_pages">&nbsp;
					<p class="pages" id="pages">&nbsp;
					</p>
				</div>

				<div id="eps_gentopic" class="eps_gentopic">
					<h2>Welcome to eGENE</h2>
				</div>
				
				<div id="eps" class="eps">
					<p>eGENE is an automatic exam paper generator. Currently it supports limited to exam peper generation of Mathematics Primary 6, for topic Decimals, based on Singapore syllabus.</p>
					<p>eGENE generates a collection of exam paper generator randomly based on predefined templates.</p>
					<p>To generate a collection of exam paper, please do following steps:<br />
						1. Select topic of exam paper to be generated.<br />
						2. Select number of questions to be generated. Allowed value is 1 till 999.<br />
						3. Select number of questions to be displayed per page as the result. Allowed value is 1 till 99.<br />
					</p>
					<p>To modify the existing templates or add new templates into eGENE, please go to Administration menu</p>
				</div>
				
			</div>

		</div>


		<div class="sidebar">


			<div class="searchform">
				<form id="formsearch" name="formsearch" method="post"
					action="search.jsp">
					<span><input name="editbox_search" class="editbox_search"
						id="editbox_search" maxlength="80" value="Search our site:"
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
						<li><label for="qtopic">Topic</label> <select id="qtopic"
							name="qtopic">
								<option id="default" value="default" selected>-- select --</option>
								<option id="decimals" value="tplm_decimals">Decimals</option>
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
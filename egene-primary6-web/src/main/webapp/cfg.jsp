<%@ include file="header.jsp"%>

<script type="text/javascript">


	function selectConfigMenu(currConfig) {		
		for ($j = 1; $j <= 2; $j++) {
			
			if ($j != currConfig) {
				$("#cfg_" + $j).removeClass("sbselected").addClass("sbunselected");
				$("#acfg_" + $j).removeClass("sbselected").addClass("sbunselected");
			} else {
				$("#cfg_" + $j).removeClass("sbunselected").addClass("sbselected");
				$("#acfg_" + $j).removeClass("sbunselected").addClass("sbselected");
			}		
			
		}
	}
	
	function selectConfigMenu1(idMenu) {
		selectConfigMenu(idMenu);
		$("#eps_config_title").html("<h2>Exam Master Topics</h2>");
		
		return false;
	}
	
	function selectConfigMenu2(idMenu) {
		selectConfigMenu(idMenu);
		$("#eps_config_title").html("<h2>Exam Data References</h2>");
		
		return false;
	}	
	

	$(document).ready(function() {
		$(".tblstrip tr").mouseover(function(){$(this).addClass("tblover");}).mouseout(function(){$(this).removeClass("tblover");});
		$(".tblstrip tr:even").addClass("tblalt");

	});
	
</script>

<div class="content">
	<div class="content_resize">
		<div class="mainbar">

			<div id="mainbar_tools" class="mainbar_tools">
			</div>

			<div class="printable">
			
				<div id="eps_config_title" class="eps_config_title">
					<h2>Exam Master Topics</h2>
				</div>
				
				<div>
					new
				</div>
				<div class="config_area">
					<table class="tblstrip">
						<thead>
							<tr>
								<th>Topics</th>
								<th>Table Master</th>
								<th class="clr"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Decimals</td>
								<td>tplm_decimals</td>
								<td><span>edit</span>&nbsp;<span>delete</span></td>
							</tr>
							<tr>
								<td>Decimals</td>
								<td>tplm_decimals</td>
								<td><span>edit</span>&nbsp;<span>delete</span></td>
							</tr>
							<tr>
								<td>Decimals</td>
								<td>tplm_decimals</td>
								<td><span>edit</span>&nbsp;<span>delete</span></td>
							</tr>
						</tbody>
					</table>
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
				<h2 class="star"><span>Configuration Menu</span></h2>
				<div class="clr"></div>
				  <ul class="sb_menu">
					<li class="sbselected" id="cfg_1"><a class="sbselected" id="acfg_1" href="#" onClick="selectConfigMenu1('1')">Manage Exam Master Topics</a></li>
					<li class="sbunselected" id="cfg_2"><a class="sbunselected" id="acfg_2" href="#" onClick="selectConfigMenu2('2')">Manage Data References</a></li>
				  </ul>				
			</div>
		</div>

		<div class="clr"></div>
	</div>
</div>


<%@ include file="footer.jsp"%>
<%@ include file="header.jsp" %>

  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
          <h2><span>Contact</span></h2><div class="clr"></div>
          <p>You can find more of my free template designs at my website. For premium commercial designs, you can check out DreamTemplate.com.</p>
        </div>
        <div class="article">
          <h2><span>Send us</span> mail</h2><div class="clr"></div>
          <form action="#" method="post" id="sendemail">
          <ol><li>
            <label for="name">Name (required)</label>
            <input id="name" name="name" class="text" />
          </li><li>
            <label for="email">Email Address (required)</label>
            <input id="email" name="email" class="text" />
          </li><li>
            <label for="website">Website</label>
            <input id="website" name="website" class="text" />
          </li><li>
            <label for="message">Your Message</label>
            <textarea id="message" name="message" rows="8" cols="50"></textarea>
          </li><li>
            <input type="image" name="imageField" id="imageField" src="images/submit.gif" class="send" />
            <div class="clr"></div>
          </li></ol>
          </form>
        </div>
      </div>
      <div class="sidebar">
        <div class="searchform">
          <form id="formsearch" name="formsearch" method="post" action="search.jsp">
            <span><input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Search our site:" type="text" /></span>
            <input name="button_search" src="images/search_btn.gif" class="button_search" type="image" />
          </form>
        </div>
        <div class="gadget">
          <h2 class="star"><span>Sidebar</span> Menu</h2><div class="clr"></div>
          <ul class="sb_menu">
            <li><a href="#">Home</a></li>
            <li><a href="#">TemplateInfo</a></li>
            <li><a href="#">Style Demo</a></li>
            <li><a href="#">Blog</a></li>
            <li><a href="#">Archives</a></li>
            <li><a href="http://www.dreamtemplate.com">Web Templates</a></li>
          </ul>
        </div>
        <div class="gadget">
          <h2 class="star"><span>Sponsors</span></h2><div class="clr"></div>
          <ul class="ex_menu">
            <li><a href="http://www.dreamtemplate.com/">DreamTemplate</a><br />Over 6,000+ Premium Web Templates</li>
            <li><a href="http://www.templatesold.com/">TemplateSOLD</a><br />Premium WordPress &amp; Joomla Themes</li>
            <li><a href="http://www.imhosted.com/">ImHosted.com</a><br />Affordable Web Hosting Provider</li>
            <li><a href="http://www.megastockphotos.com/">MegaStockPhotos</a><br />Unlimited Amazing Stock Photos</li>
            <li><a href="http://www.evrsoft.com/">Evrsoft</a><br />Website Builder Software &amp; Tools</li>
            <li><a href="http://www.csshub.com/">CSS Hub</a><br />Premium CSS Templates</li>
          </ul>
        </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>

<%@ include file="fbg.jsp" %>
<%@ include file="footer.jsp" %>
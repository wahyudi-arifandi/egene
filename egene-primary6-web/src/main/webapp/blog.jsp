<%@ include file="header.jsp" %>

  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
          <h2><span>A Blog</span> Entry</h2><div class="clr"></div>
          <p>Posted by <a href="#">Webmaster</a>  <span>&nbsp;&bull;&nbsp;</span>  Filed under <a href="#">templates</a>, <a href="#">internet</a></p>
          <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam. Cras fringilla magna. Phasellus suscipit, leo a pharetra condimentum, lorem tellus eleifend magna, eget fringilla velit magna id neque. Curabitur vel urna. In tristique orci porttitor ipsum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo. </p>
          <p>Tagged: <a href="#">orci</a>, <a href="#">lectus</a>, <a href="#">varius</a>, <a href="#">turpis</a></p>
          <p><a href="#"><strong>Comments (3)</strong></a>  <span>&nbsp;&bull;&nbsp;</span>  May 27, 2010  <span>&nbsp;&bull;&nbsp;</span>  <a href="#"><strong>Edit</strong></a></p>
        </div>
        <div class="article">
          <h2><span>3</span> Responses</h2><div class="clr"></div>
          <div class="comment">
            <a href="#"><img src="images/userpic.gif" width="40" height="40" alt="user" class="userpic" /></a>
            <p><a href="#">admin</a> Says:<br />April 20th, 2009 at 2:17 pm</p>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum.</p>
          </div>
          <div class="comment">
            <a href="#"><img src="images/userpic.gif" width="40" height="40" alt="user" class="userpic" /></a>
            <p><a href="#">Webmaster</a> Says:<br />April 20th, 2009 at 3:21 pm</p>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo.</p>
          </div>
          <div class="comment">
            <a href="#"><img src="images/userpic.gif" width="40" height="40" alt="user" class="userpic" /></a>
            <p><a href="#">admin</a> Says:<br />April 20th, 2009 at 2:17 pm</p>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum.</p>
          </div>
        </div>
        <div class="article">
          <h2><span>Leave a</span> Reply</h2><div class="clr"></div>
          <form action="#" method="post" id="leavereply">
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
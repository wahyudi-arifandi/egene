package edu.ntu.eee.csn.ism.egene.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;

@WebServlet(description = "Generate MSWord", urlPatterns = { "/SvCreateMSWord" })
public class SvCreateMSWord extends HttpServlet {

	private static final long serialVersionUID = 4775347024417804208L;
	private static Logger LOGGER = Logger.getLogger(SvCreateMSWord.class);

	@Override
	public void init() {
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("init:started");

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("init:ended");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doGet:started");

			Enumeration<String> params = request.getParameterNames();
			StringBuilder sb = new StringBuilder("[");
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				String val = request.getParameter(param);
				sb.append("[").append(param).append(" = ").append(val)
						.append("], ");
			}
			sb.append("]");

			LOGGER.debug("params=" + sb.toString());
		}

		response.setContentType("application/msword; charset=UTF-8");
		response.setHeader("Content-disposition", "inline; filename="
				+ "exam-paper.doc");
		this.generateMSWord(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doGet:ended");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("doPost:started");

			Enumeration<String> params = request.getParameterNames();
			StringBuilder sb = new StringBuilder("[");
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				String val = request.getParameter(param);
				sb.append("[").append(param).append(" = ").append(val)
						.append("], ");
			}
			sb.append("]");

			LOGGER.debug("params=" + sb.toString());
		}

		response.setContentType("application/msword; charset=UTF-8");
		response.setHeader("Content-disposition", "inline; filename="
				+ "exam-paper.doc");
		this.generateMSWord(request, response);
		response.getOutputStream().flush();
		response.getOutputStream().close();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("doPost:ended");

	}

	private void generateMSWord(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean solution = Boolean.parseBoolean(request
				.getParameter("solution"));
		String xmlStr = request.getParameter("egene");

		IDocument myDoc = new Document2004();

		// header
		myDoc.addEle(Heading1.with("Exam Paper").create());

		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlStr));

			Document doc = db.parse(is);
			NodeList nodesEps = doc.getElementsByTagName("eps");
			Node nodeEps = nodesEps.item(0);

			NodeList nodesSubject = doc.getElementsByTagName("subject");
			Node nodeSubject = nodesSubject.item(0);

			NodeList nodesTopic = doc.getElementsByTagName("topic");
			Node nodeTopic = nodesTopic.item(0);

			NodeList nodesLevel = doc.getElementsByTagName("level");
			Node nodeLevel = nodesLevel.item(0);

			NodeList nodesGenDate = doc.getElementsByTagName("generated-date");
			Node nodeGenDate = nodesGenDate.item(0);

			myDoc.addEle(Paragraph.with(nodeSubject.getTextContent()).create());
			myDoc.addEle(Paragraph.with(nodeTopic.getTextContent()).create());
			myDoc.addEle(Paragraph.with(nodeLevel.getTextContent()).create());
			myDoc.addEle(Paragraph.with(nodeGenDate.getTextContent()).create());

			NodeList nodesEp = ((Element) nodeEps).getElementsByTagName("ep");
			for (int i = 0; i < nodesEp.getLength(); i++) {

				Node nodeEp = nodesEp.item(i);

				NodeList nodesId = ((Element) nodeEp)
						.getElementsByTagName("id");
				Element elmId = (Element) nodesId.item(0);
				String id = elmId.getTextContent();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("id = [" + id + "]");

				NodeList nodesQuestion = ((Element) nodeEp)
						.getElementsByTagName("question");
				Element elmQuestion = (Element) nodesQuestion.item(0);
				String question = elmQuestion.getTextContent();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("question = [" + question + "]");

				NodeList nodesAnswerStep = ((Element) nodeEp)
						.getElementsByTagName("answer-step");
				Element elmAnswerStep = (Element) nodesAnswerStep.item(0);
				String answerStep = elmAnswerStep.getTextContent();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("answerStep = [" + answerStep + "]");

				NodeList nodesAnswerFinal = ((Element) nodeEp)
						.getElementsByTagName("answer-final");
				Element elmAnswerFinal = (Element) nodesAnswerFinal.item(0);
				String answerFinal = elmAnswerFinal.getTextContent();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("answerFinal = [" + answerFinal + "]");

				NodeList nodesSolutionStep = ((Element) nodeEp)
						.getElementsByTagName("solution-step");
				Element elmSolutionStep = (Element) nodesSolutionStep.item(0);
				String solutionStep = elmSolutionStep.getTextContent();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("solutionStep = [" + solutionStep + "]");

				NodeList nodesSolutionFinal = ((Element) nodeEp)
						.getElementsByTagName("solution-final");
				Element elmSolutionFinal = (Element) nodesSolutionFinal.item(0);
				String solutionFinal = elmSolutionFinal.getTextContent();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("solutionFinal = [" + solutionFinal + "]");

				// question
				myDoc.addEle(Heading2.with("Question " + id + ":").create());
				this.addEleParagraph(myDoc, question);
				myDoc.addEle(new BreakLine(1));

				// answer step
				ParagraphPiece piece1 = ParagraphPiece.with("Answer-steps:")
						.withStyle().bold().create();
				myDoc.addEle(Paragraph.withPieces(piece1));
				this.addEleParagraph(myDoc, answerStep);
				myDoc.addEle(new BreakLine(1));

				// answer final
				ParagraphPiece piece2 = ParagraphPiece.with("Answer-final:")
						.withStyle().bold().create();
				myDoc.addEle(Paragraph.withPieces(piece2));
				this.addEleParagraph(myDoc, answerFinal);
				myDoc.addEle(new BreakLine(1));

				if (solution) {
					// solution step
					ParagraphPiece piece3 = ParagraphPiece
							.with("Solution-steps:").withStyle().bold()
							.create();
					myDoc.addEle(Paragraph.withPieces(piece3));
					this.addEleParagraph(myDoc, solutionStep);
					myDoc.addEle(new BreakLine(1));

					// solution final
					ParagraphPiece piece4 = ParagraphPiece
							.with("Solution-final:").withStyle().bold()
							.create();
					myDoc.addEle(Paragraph.withPieces(piece4));
					this.addEleParagraph(myDoc, solutionFinal);
					myDoc.addEle(new BreakLine(1));
				}

			}

		} catch (ParserConfigurationException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
		} catch (SAXException e) {
			if (LOGGER.isEnabledFor(Level.ERROR))
				LOGGER.error(e.getMessage(), e);
		}

		String myWord = myDoc.getContent();

		PrintWriter writer = response.getWriter();
		writer.println(myWord);

	}

	private void addEleParagraph(IDocument myDoc, String str) {

		String newLineUnix = "\n";
		str = this.dos2unix(str);
		String[] strElm = str.split(newLineUnix);

		for (int i = 0; i < strElm.length; i++) {
			myDoc.addEle(Paragraph.with(strElm[i]).create());
		}

	}

	@SuppressWarnings("unused")
	private String unix2dos(String str) {

		String newLineDos = "\r\n";
		String newLineUnix = "\n";

		str = str.replaceAll(newLineDos, newLineUnix);
		return str.replaceAll(newLineUnix, newLineDos);

	}

	private String dos2unix(String str) {

		String newLineDos = "\r\n";
		String newLineUnix = "\n";

		return str.replaceAll(newLineDos, newLineUnix);
	}

}

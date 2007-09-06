/*
 [The "BSD licence"]
 Copyright (c) 2005-2006 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.eclipse.persistence.internal.libraries.antlr.runtime.debug;

import org.eclipse.persistence.internal.libraries.antlr.runtime.RecognitionException;
import org.eclipse.persistence.internal.libraries.antlr.runtime.Token;
import org.eclipse.persistence.internal.libraries.antlr.runtime.tree.ParseTree;

import java.util.Stack;

/** This parser listener tracks rule entry/exit and token matches
 *  to build a simple parse tree using ParseTree nodes.
 */
public class ParseTreeBuilder extends BlankDebugEventListener {
	Stack callStack = new Stack();

	public ParseTreeBuilder(String grammarName) {
		ParseTree root = create("<grammar "+grammarName+">");
		callStack.push(root);
	}

	public ParseTree getTree() {
		return (ParseTree)callStack.elementAt(0);
	}

	/**  What kind of node to create.  You might want to override
	 *   so I factored out creation here.
	 */
	public ParseTree create(Object payload) {
		return new ParseTree(payload);
	}

	public void enterRule(String ruleName) {
		ParseTree parentRuleNode = (ParseTree)callStack.peek();
		ParseTree ruleNode = create(ruleName);
		parentRuleNode.addChild(ruleNode);
		callStack.push(ruleNode);
	}

	public void exitRule(String ruleName) {
		callStack.pop();
	}

	public void consumeToken(Token token) {
		ParseTree ruleNode = (ParseTree)callStack.peek();
		ParseTree elementNode = create(token);
		ruleNode.addChild(elementNode);
	}

	public void recognitionException(RecognitionException e) {
		ParseTree ruleNode = (ParseTree)callStack.peek();
		ParseTree errorNode = create(e);
		ruleNode.addChild(errorNode);
	}
}

/*
 * Copyright 2020 The Context Mapper Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.contextmapper.dsl.ide.commands.impl.refactoring;

import java.util.Set;

import org.contextmapper.dsl.ide.edit.WorkspaceEditRecorder;
import org.contextmapper.dsl.refactoring.DeriveBoundedContextFromSubdomains;
import org.contextmapper.dsl.refactoring.SemanticCMLRefactoring;
import org.eclipse.lsp4j.ExecuteCommandParams;

import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class DeriveBoundedContextFromSubdomainsCommand extends AbstractRefactoringCommand {

	public DeriveBoundedContextFromSubdomainsCommand(WorkspaceEditRecorder editRecorder) {
		super(editRecorder);
	}

	@Override
	protected SemanticCMLRefactoring getRefactoring(ExecuteCommandParams params) {
		JsonArray refactoringParams = (JsonArray) params.getArguments().get(1);

		JsonPrimitive boundedContextName = (JsonPrimitive) refactoringParams.get(0);
		JsonArray selectedSubdomainsArray = (JsonArray) refactoringParams.get(1);

		Set<String> selectedSubdomains = Sets.newHashSet();
		for (JsonElement element : selectedSubdomainsArray) {
			selectedSubdomains.add(element.getAsString());
		}

		return new DeriveBoundedContextFromSubdomains(boundedContextName.getAsString(), selectedSubdomains);
	}

}

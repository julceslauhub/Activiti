/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.workflow.simple.alfresco.conversion.script;

import org.activiti.bpmn.model.FieldExtension;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.workflow.simple.alfresco.conversion.AlfrescoConversionConstants;

/**
 * Builder to create a service-task that executes Alfresco javascript.
 * 
 * @author Frederik Heremans
 */
public class ScriptServiceTaskBuilder {

	protected StringBuilder finalScript;
	protected ServiceTask serviceTask;
	
	public ScriptServiceTaskBuilder() {
		finalScript = new StringBuilder();
  }
	
	public ServiceTask build() {
		if(serviceTask == null) {
			FieldExtension scriptFieldElement = new FieldExtension();
			scriptFieldElement.setFieldName(AlfrescoConversionConstants.SCRIPT_DELEGATE_SCRIPT_FIELD_NAME);
			scriptFieldElement.setExpression(finalScript.toString());
			
			serviceTask = new ServiceTask();
			serviceTask.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
			serviceTask.setImplementation(AlfrescoConversionConstants.CLASSNAME_SCRIPT_DELEGATE);
			serviceTask.getFieldExtensions().add(scriptFieldElement);
		}
		return serviceTask;
	}
	

	public void addLine(String line) {
		finalScript.append(line).append("\n");
	}
}

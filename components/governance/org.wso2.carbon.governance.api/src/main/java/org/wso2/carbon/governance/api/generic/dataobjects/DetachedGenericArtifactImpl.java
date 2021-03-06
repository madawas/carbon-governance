/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.governance.api.generic.dataobjects;

import org.wso2.carbon.governance.api.common.dataobjects.GovernanceArtifact;
import org.wso2.carbon.governance.api.exception.GovernanceException;
import org.wso2.carbon.governance.api.generic.GenericArtifactManager;

import javax.xml.namespace.QName;

public class DetachedGenericArtifactImpl extends GenericArtifactImpl implements DetachedGenericArtifact {

    protected DetachedGenericArtifactImpl(GovernanceArtifact artifact,
                                          String mediaType) {
        super(artifact, mediaType);
    }

    public DetachedGenericArtifactImpl(QName artifactName, String mediaType) {
        super(artifactName, mediaType);
    }

    @Override
    public GenericArtifact makeRegistryAware(GenericArtifactManager artifactManager)
            throws GovernanceException {
        GenericArtifact newArtifact = artifactManager.newGovernanceArtifact(getQName());
        for (String key : getAttributeKeys()) {
            for (String attribute : getAttributes(key)) {
                newArtifact.addAttribute(key, attribute);
            }
        }
        return newArtifact;
    }

    @Override
    public GenericArtifact makeRegistryAware(String id, GenericArtifactManager artifactManager) throws GovernanceException {
        GenericArtifact artifact = artifactManager.getGenericArtifact(id);
        for (String key : getAttributeKeys()) {
            for (String attribute : getAttributes(key)) {
                artifact.setAttribute(key, attribute);
            }
        }
        return artifact;
    }
}

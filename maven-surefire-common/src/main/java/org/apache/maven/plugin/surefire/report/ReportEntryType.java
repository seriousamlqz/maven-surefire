package org.apache.maven.plugin.surefire.report;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

public enum ReportEntryType
{
    error
        {
            @Override
            public String getXmlTag()
            {
                return "error";
            }

            @Override
            public String getFlakyXmlTag()
            {
                return "flakyError";
            }

            @Override
            public String getRerunXmlTag()
            {
                return "rerunError";
            }
        },

    failure
        {
            @Override
            public String getXmlTag()
            {
                return "failure";
            }

            @Override
            public String getFlakyXmlTag()
            {
                return "flakyFailure";
            }

            @Override
            public String getRerunXmlTag()
            {
                return "rerunFailure";
            }
        },

    skipped
        {
            @Override
            public String getXmlTag()
            {
                return "skipped";
            }

            @Override
            public String getFlakyXmlTag()
            {
                return "";
            }

            @Override
            public String getRerunXmlTag()
            {
                return "";
            }
        },

    success
        {
            public String getXmlTag()
            {
                return "";
            }

            @Override
            public String getFlakyXmlTag()
            {
                return "";
            }

            @Override
            public String getRerunXmlTag()
            {
                return "";
            }
        };

    public abstract String getXmlTag();

    public abstract String getFlakyXmlTag();

    public abstract String getRerunXmlTag();

}

/*
 * Copyright (c) 1998, 2024 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.config;

/**
 * PUBLIC:
 * This interface is to allow extra customization on an EclipseLink Session
 * @deprecated Use {@link org.eclipse.persistence.sessions.SessionCustomizer}
 */
    /**
     * @deprecated This constructor will be marked private and the class final. It is not designed for extensibility.
     */
    @Deprecated(since = "4.0.3", forRemoval = true)

public interface SessionCustomizer extends org.eclipse.persistence.sessions.SessionCustomizer {
}

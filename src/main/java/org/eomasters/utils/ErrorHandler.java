/*-
 * ========================LICENSE_START=================================
 * EOMTBX - EOMasters Toolbox for SNAP
 * -> https://www.eomasters.org/sw/EOMTBX
 * ======================================================================
 * Copyright (C) 2023 Marco Peters
 * ======================================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * -> http://www.gnu.org/licenses/gpl-3.0.html
 * =========================LICENSE_END==================================
 */

package org.eomasters.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eomasters.gui.Dialogs;

/**
 * Handles errors.
 */
public class ErrorHandler {

  protected static final Logger LOG = Logger.getLogger("org.eomasters");

  /**
   * Shows a simple error dialog.
   *
   * @param title   the title
   * @param message the error message
   */
  public static void handleError(String title, String message) {
    LOG.log(Level.WARNING, String.format("%s: %s", title, message));
    if (SystemHelper.isHeadless()) {
      return;
    }
    Dialogs.error(title, message, null);
  }

  /**
   * Shows an error dialog extended by the cause exception.
   *
   * @param title     the title
   * @param message   the error message
   * @param exception the cause exception
   */
  public static void handleError(String title, String message, Exception exception) {
    LOG.log(Level.WARNING, message, exception);
    if (SystemHelper.isHeadless()) {
      return;
    }
    Dialogs.error(title, message, exception);
  }


}

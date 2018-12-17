#!/usr/bin/env bash
#
#  LICENSE
#
#  This file is part of the GLPI API Client Library for Java,
#  a subproject of GLPI. GLPI is a free IT Asset Management.
#
#  GLPI is free software: you can redistribute it and/or
#  modify it under the terms of the GNU General Public License
#  as published by the Free Software Foundation; either version 3
#  of the License, or (at your option) any later version.
#
#  GLPI is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  -------------------------------------------------------------------------------
#  @copyright Copyright © 2018 Teclib. All rights reserved.
#  @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
#  @link      https://github.com/glpi-project/java-library-glpi
#  @link      http://www.glpi-project.org/
#  @link      https://glpi-project.github.io/java-library-glpi/
#  @link      https://bintray.com/glpi-project/teclib-repository/java-library-glpi
#  -------------------------------------------------------------------------------
#

# Push commits and tags to origin branch
git push --follow-tags origin $CIRCLE_BRANCH
#
## Merge back the develop branch step
#
## go to develop
#git checkout develop
#git add .
#git stash
#
## merge with master
#git rebase master
#
#
## push develop
#git push origin develop
#
## return to master
#git checkout master
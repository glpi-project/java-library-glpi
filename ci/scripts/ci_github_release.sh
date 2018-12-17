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

GIT_TAG=$(jq -r ".version" package.json)

# Push commits and tags to origin branch
# git push --follow-tags origin $CIRCLE_BRANCH

# push tag to github
conventional-github-releaser -p angular -t $GITHUB_TOKEN 2> /dev/null || true

# Create zip example code
sudo zip -r $CIRCLE_ARTIFACTS/java_example_code.zip example/*

# Update release name
github-release edit \
--user $CIRCLE_PROJECT_USERNAME \
--repo $CIRCLE_PROJECT_REPONAME \
--tag ${GIT_TAG} \
--name "GLPI API REST v${GIT_TAG}" \

# Upload example code release
github-release upload \
--user $CIRCLE_PROJECT_USERNAME \
--repo $CIRCLE_PROJECT_REPONAME \
--tag ${GIT_TAG} \
--name "java_example_${GIT_TAG}.zip" \
--file $CIRCLE_ARTIFACTS/java_example_code.zip
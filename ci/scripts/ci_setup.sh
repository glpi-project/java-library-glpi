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

# create enviroment vars to work with fastlane telegram
echo TELEGRAM_WEBHOOKS=$TELEGRAM_WEBHOOKS > .env
echo GIT_REPO=$CIRCLE_REPOSITORY_URL >> .env
echo GIT_BRANCH=$CIRCLE_BRANCH >> .env

# install ruby
sudo apt-get install ruby-full build-essential

# install fastlane
sudo gem install fastlane --no-rdoc --no-ri

# install Node.js v7
curl -sL https://deb.nodesource.com/setup_7.x | sudo -E bash -
sudo sudo apt-get install -y nodejs

# install globally
sudo npm install -g conventional-github-releaser

# Install node-github-release to create and edit releases on Github
sudo npm install -g node-github-release

# install node package available on package.json
sudo npm install

# config git
git config --global user.email $GH_EMAIL
git config --global user.name "Teclib'bot"
git remote remove origin
git remote add origin https://$GH_USER:$GH_TOKEN@github.com/$CIRCLE_PROJECT_USERNAME/$CIRCLE_PROJECT_REPONAME.git
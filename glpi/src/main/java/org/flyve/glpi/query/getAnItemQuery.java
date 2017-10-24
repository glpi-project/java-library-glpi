package org.flyve.glpi.query;

import java.util.HashMap;
import java.util.Map;

/*
 *   Copyright © 2017 Teclib. All rights reserved.
 *
 *   This file is part of flyve-mdm-android
 *
 * flyve-mdm-android is a subproject of Flyve MDM. Flyve MDM is a mobile
 * device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @date      24/10/17
 * @copyright Copyright © 2017 Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/flyve-mdm-android
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */
public class getAnItemQuery {

    private Boolean expandDropdowns;
    private Boolean getHateoas;
    private Boolean getSha1;
    private Boolean withDevices;
    private Boolean withDisks;
    private Boolean withSoftwares;
    private Boolean withConnections;
    private Boolean withNetworkports;
    private Boolean withInfocoms;
    private Boolean withContracts;
    private Boolean withDocuments;
    private Boolean withTickets;
    private Boolean withProblems;
    private Boolean withChanges;
    private Boolean withNotes;
    private Boolean withLogs;

    public Map<String, String> getQuery() {

        Map<String, String> map = new HashMap<>();

        if(expandDropdowns!=null) {
            map.put("expand_dropdowns", expandDropdowns.toString());
        }

        if(getHateoas!=null) {
            map.put("get_hateoas", getHateoas.toString());
        }

        if(getSha1!=null) {
            map.put("get_sha1", getSha1.toString());
        }

        if(withDevices!=null) {
            map.put("with_devices", withDevices.toString());
        }

        if(withDisks!=null) {
            map.put("with_disks", withDisks.toString());
        }

        if(withSoftwares!=null) {
            map.put("with_softwares", withSoftwares.toString());
        }

        if(withConnections!=null) {
            map.put("with_connections", withConnections.toString());
        }

        if(withNetworkports!=null) {
            map.put("with_networkports", withNetworkports.toString());
        }

        if(withInfocoms!=null) {
            map.put("with_infocoms", withInfocoms.toString());
        }

        if(withContracts!=null) {
            map.put("with_contracts", withContracts.toString());
        }

        if(withDocuments!=null) {
            map.put("with_documents", withDocuments.toString());
        }

        if(withTickets!=null) {
            map.put("with_tickets", withTickets.toString());
        }

        if(withProblems!=null) {
            map.put("with_problems", withProblems.toString());
        }

        if(withChanges!=null) {
            map.put("with_changes", withChanges.toString());
        }

        if(withNotes!=null) {
            map.put("with_notes", withNotes.toString());
        }

        if(withLogs!=null) {
            map.put("with_logs", withLogs.toString());
        }

        return map;
    }

    public void setExpandDropdowns(Boolean expandDropdowns) {
        this.expandDropdowns = expandDropdowns;
    }

    public void setGetHateoas(Boolean getHateoas) {
        this.getHateoas = getHateoas;
    }

    public void setGetSha1(Boolean getSha1) {
        this.getSha1 = getSha1;
    }

    public void setWithDevices(Boolean withDevices) {
        this.withDevices = withDevices;
    }

    public void setWithDisks(Boolean withDisks) {
        this.withDisks = withDisks;
    }

    public void setWithSoftwares(Boolean withSoftwares) {
        this.withSoftwares = withSoftwares;
    }

    public void setWithConnections(Boolean withConnections) {
        this.withConnections = withConnections;
    }

    public void setWithNetworkports(Boolean withNetworkports) {
        this.withNetworkports = withNetworkports;
    }

    public void setWithInfocoms(Boolean withInfocoms) {
        this.withInfocoms = withInfocoms;
    }

    public void setWithContracts(Boolean withContracts) {
        this.withContracts = withContracts;
    }

    public void setWithDocuments(Boolean withDocuments) {
        this.withDocuments = withDocuments;
    }

    public void setWithTickets(Boolean withTickets) {
        this.withTickets = withTickets;
    }

    public void setWithProblems(Boolean withProblems) {
        this.withProblems = withProblems;
    }

    public void setWithChanges(Boolean withChanges) {
        this.withChanges = withChanges;
    }

    public void setWithNotes(Boolean withNotes) {
        this.withNotes = withNotes;
    }

    public void setWithLogs(Boolean withLogs) {
        this.withLogs = withLogs;
    }
}

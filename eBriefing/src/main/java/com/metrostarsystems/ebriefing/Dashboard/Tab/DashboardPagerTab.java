/*
Copyright (C) 2017 MetroStar Systems

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

The full license text can be found is the included LICENSE file.

You can freely use any of this software which you make publicly
available at no charge.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package com.metrostarsystems.ebriefing.Dashboard.Tab;

import com.metrostarsystems.ebriefing.MainApplication;
import com.metrostarsystems.ebriefing.Dashboard.Tab.DashboardTabs.DashboardTab;
import com.metrostarsystems.ebriefing.Data.Framework.AbstractPagerFragment;
import com.metrostarsystems.ebriefing.Data.Framework.AbstractPagerTab;

public class DashboardPagerTab extends AbstractPagerTab<AbstractPagerFragment, DashboardTab> {
	
	private DashboardPagerTab(Builder build) {
		super(build);
	}

	
	public static class Builder extends AbstractPagerTab.Builder<AbstractPagerFragment, DashboardTab> {

		public Builder(MainApplication app) {
			super(app);
		}
		
		public DashboardPagerTab build() {
			return new DashboardPagerTab(this);
		}
		
	} 

}

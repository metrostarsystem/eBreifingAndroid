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

import java.util.ArrayList;

import com.metrostarsystems.ebriefing.MainApplication;
import com.metrostarsystems.ebriefing.Dashboard.TabAvailable.FragmentAvailable;
import com.metrostarsystems.ebriefing.Dashboard.TabFavorites.FragmentFavorites;
import com.metrostarsystems.ebriefing.Dashboard.TabMyBooks.FragmentMyBooks;
import com.metrostarsystems.ebriefing.Dashboard.TabUpdates.FragmentUpdates;
import com.metrostarsystems.ebriefing.Data.Framework.AbstractPagerFragment;

public class DashboardTabs {

	private MainApplication mApp;
	private ArrayList<DashboardPagerTab> mTabs;
	
	public DashboardTabs(MainApplication main) {
		mApp = main;
		
		mTabs = new ArrayList<DashboardPagerTab>();
		
		mTabs.add((DashboardPagerTab) new DashboardPagerTab.Builder(mApp)
												.tab(DashboardTab.TAB_MYBOOKS)
												.title("My Books")
												.fragment(FragmentMyBooks.newInstance())
												.indicatorColor("#FF004D95")
												.dividerColor("#FF888888")
												.build());
		mTabs.add((DashboardPagerTab) new DashboardPagerTab.Builder(mApp)
												.tab(DashboardTab.TAB_FAVORITES)
												.title("Favorites")
												.fragment(FragmentFavorites.newInstance())
												.indicatorColor("#FF004D95")
												.dividerColor("#FF888888")
												.build());
		
		mTabs.add((DashboardPagerTab) new DashboardPagerTab.Builder(mApp)
												.tab(DashboardTab.TAB_AVAILABLE)
												.title("Available")
												.fragment(FragmentAvailable.newInstance())
												.indicatorColor("#FF004D95")
												.dividerColor("#FF888888")
												.build());
		
		mTabs.add((DashboardPagerTab) new DashboardPagerTab.Builder(mApp)
												.tab(DashboardTab.TAB_UPDATES)
												.title("Updates")
												.fragment(FragmentUpdates.newInstance())
												.indicatorColor("#FF004D95")
												.dividerColor("#FF888888")
												.build());
	}
	
	public int total() {
		return mTabs.size();
	}
	
	public String tabTitle(int position) {
		DashboardPagerTab tab = mTabs.get(position);
		
		return tab.title();
	}
	
	public int tabIndicatorColor(int position) {
		DashboardPagerTab tab = mTabs.get(position);
		
		return tab.indicatorColor();
	}
	
	public int tabDividerColor(int position) {
		DashboardPagerTab tab = mTabs.get(position);
		
		return tab.dividerColor();
	}
	
	public AbstractPagerFragment processTab(int index) {
		DashboardPagerTab tab = mTabs.get(index);
		
		return (AbstractPagerFragment) tab.fragment();
	}
	
	public void refresh() {
		for(int index = 0; index < mTabs.size(); index++) {
			DashboardPagerTab tab = mTabs.get(index);
		
			tab.fragment().refresh();
		}
	}
	
	public static enum DashboardTab {
		TAB_MYBOOKS,
		TAB_FAVORITES,
		TAB_AVAILABLE,
		TAB_UPDATES;
	}
}

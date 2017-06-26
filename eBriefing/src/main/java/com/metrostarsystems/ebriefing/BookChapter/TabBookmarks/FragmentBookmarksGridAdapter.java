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

package com.metrostarsystems.ebriefing.BookChapter.TabBookmarks;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metrostarsystems.ebriefing.MainApplication;
import com.metrostarsystems.ebriefing.R;
import com.metrostarsystems.ebriefing.BookChapter.Tab.ChapterTabs.ChapterTab;
import com.metrostarsystems.ebriefing.Data.Framework.Book.Book;
import com.metrostarsystems.ebriefing.Data.Framework.Bookmarks.Bookmark;
import com.metrostarsystems.ebriefing.Data.Framework.Managers.ManagerImages.GeneratePageThumbnailTask;

public class FragmentBookmarksGridAdapter extends ArrayAdapter<Bookmark> {

	private MainApplication mApp;
	private Context mContext;
	private ChapterTab mTab;
	private Book mBook;
	
	public FragmentBookmarksGridAdapter(Context context, ChapterTab tab, Book book) {
		super(context, R.layout.activity_chapter_bookmark_grid_list_item);
		
		mApp = (MainApplication) context.getApplicationContext();
		mContext = context;
		mTab = tab;
		mBook = book;
		
		addAll(mApp.data().database().bookmarksDatabase().bookmarksByBook(mBook.id()));
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		// If convertview is null create the layout
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.activity_chapter_bookmark_grid_list_item, parent, false);
			
			// Create the view holder
			holder = new ViewHolder();
			holder.mIncludeItem = (LinearLayout) convertView.findViewById(R.id.include_list_item);
			holder.mPageImageView = (ImageView) holder.mIncludeItem.findViewById(R.id.imageView_thumbnail);
			holder.mBookmarkImageView = (ImageView) holder.mIncludeItem.findViewById(R.id.imageView_bookmark);
			holder.mPageNumberTextView = (TextView) holder.mIncludeItem.findViewById(R.id.textView_page_number);
			holder.mNameTextView = (TextView) convertView.findViewById(R.id.textView_name);
			
			convertView.setTag(holder);
		} else {
			// Reuse the view holder
			holder = (ViewHolder) convertView.getTag();
		}
		
		Bookmark bookmark = getItem(position);
		
		try {
			if(holder.mThumbnailTask != null) {
				if(holder.mThumbnailTask.getStatus() == AsyncTask.Status.RUNNING) {
					holder.mThumbnailTask.cancel(true);
				}
			}
			
			holder.mThumbnailTask = new GeneratePageThumbnailTask(holder.mPageImageView, mBook, true);
			holder.mThumbnailTask.execute(bookmark.pageNumber());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		holder.mBookmarkImageView.setVisibility(View.VISIBLE);
		
		holder.mPageNumberTextView.setText(String.valueOf(bookmark.pageNumber()));
    	
		holder.mNameTextView.setText(bookmark.value());
		
		
		return convertView;
	}
	
	public void refresh(Book book) {
		clear();
		mBook = book;
		addAll(mApp.data().database().bookmarksDatabase().bookmarksByBook(book.id()));
	}
	
	private class ViewHolder {
		public GeneratePageThumbnailTask mThumbnailTask;
		public LinearLayout mIncludeItem;
		public ImageView mPageImageView;
		public ImageView mBookmarkImageView;
		public TextView  mPageNumberTextView;
		public TextView mNameTextView;
	}

}

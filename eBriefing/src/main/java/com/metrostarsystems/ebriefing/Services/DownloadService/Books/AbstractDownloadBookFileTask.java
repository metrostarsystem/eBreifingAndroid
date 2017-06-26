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

package com.metrostarsystems.ebriefing.Services.DownloadService.Books;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import net.maxters.android.ntlm.NTLM;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.metrostarsystems.ebriefing.MainApplication;

import android.content.Context;
import android.os.AsyncTask;

public abstract class AbstractDownloadBookFileTask extends AsyncTask<Void, Void, Boolean> {
	
	public static final int						BUFFER_SIZE = 1024 * 50;
	public static final NumberFormat 			RATE_FORMAT = new DecimalFormat("0.00");

	private static HttpParams 					mHttpParams;
	private static SchemeRegistry 				mSchemeRegistry;
	private static ClientConnectionManager 		mCCManager;
	private static DefaultHttpClient 			mHttpClient;
	protected static HttpGet 					mHttpget = new HttpGet();
	protected static File 						mDirectoryFile = null;
	
	private MainApplication 			mApp;
	protected Context						mContext;
	
	protected DownloadBookFile 			mDownload;
	protected Object 						mObject;
	protected long						mFileSize = 0;
	
	static {
		mHttpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(mHttpParams, 5000);
		HttpConnectionParams.setSoTimeout(mHttpParams, 5000);
		
		mSchemeRegistry = new SchemeRegistry();
		mSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		mSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		
		mCCManager = new ThreadSafeClientConnManager(mHttpParams, mSchemeRegistry);
	}
	
	public AbstractDownloadBookFileTask(MainApplication app, DownloadBookFile object) {
		mApp = app;
		mContext = app.getApplicationContext();
		mDownload = object;
		mObject = mDownload.object();
		
		if(mDirectoryFile == null) {
			mDirectoryFile = mContext.getExternalFilesDir(null);
		}
		
		mHttpClient = new DefaultHttpClient(mCCManager, mHttpParams);
		NTLM.setNTLM(mHttpClient,   mApp.serverConnection().userId(), 
									mApp.serverConnection().password(), 
									mApp.serverConnection().domain());
	}
	
	protected Context context() {
		return mContext;
	}
	
	protected abstract Boolean doInBackground(Void... params);
	protected abstract boolean doResponse(String directory, String fileName);
	
	protected HttpResponse executeHttp(HttpGet httpget) throws Exception {
		HttpResponse response = null;
        
		response = mHttpClient.execute(httpget);
		
		if(response == null) {
			response = mHttpClient.execute(httpget);
		}
		
		return response;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
	}
	
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}
	
	public long fileSize() {
		return mFileSize;
	}
	
	public DownloadBookFile file() {
		return mDownload;
	}

}

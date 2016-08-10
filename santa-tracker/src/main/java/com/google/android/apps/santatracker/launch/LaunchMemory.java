/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.santatracker.launch;

import android.content.Intent;
import android.view.View;

import com.google.android.apps.santatracker.R;
import com.google.android.apps.santatracker.games.MemoryActivity;

/**
 * Launch the Memory Match game.
 */
public class LaunchMemory extends AbstractLaunch {

    public LaunchMemory(SantaContext context, LauncherDataChangedCallback adapter) {
        super(context, adapter, R.string.memory, R.drawable.android_game_cards_memory_match);
    }

    static public int getId() {
        return R.string.memory;
    }

    @Override
    public void onClick(View v) {
        switch (mState) {
            case STATE_READY:
            case STATE_FINISHED:
                mContext.launchActivityDelayed(
                        new Intent(mContext.getActivityContext(), MemoryActivity.class), v);
                break;
            case STATE_DISABLED:
                notify(mContext.getApplicationContext(), R.string.memory_disabled);
                break;
            case STATE_LOCKED:
            default:
                notify(mContext.getApplicationContext(), R.string.memory_locked);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (mState) {
            case STATE_READY:
            case STATE_FINISHED:
                notify(mContext.getApplicationContext(), R.string.memory);
                break;
            case STATE_DISABLED:
                notify(mContext.getApplicationContext(), R.string.memory_disabled);
                break;
            case STATE_LOCKED:
            default:
                notify(mContext.getApplicationContext(), R.string.memory_locked);
                break;
        }
        return true;
    }

    @Override
    public boolean handleVoiceAction(Intent intent) {
        return clickIfMatchesDescription(intent, VoiceAction.ACTION_PLAY_GAME,
                VoiceAction.ACTION_PLAY_GAME_EXTRA);
    }

}

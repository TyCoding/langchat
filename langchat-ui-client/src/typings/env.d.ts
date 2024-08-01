/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_GLOB_API_URL: string;
  readonly VITE_APP_API_BASE_URL: string;
  readonly VITE_GLOB_OPEN_LONG_REPLY: string;
  readonly VITE_GLOB_APP_PWA: string;
  readonly VITE_WATER_MARK: string;
}

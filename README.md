# Islami

<img width="1920" height="1080" alt="Cover" src="https://github.com/user-attachments/assets/230958aa-1abd-43ec-9367-4e6b642d8b59" />


A native Android app for daily Islamic practice — read the Quran, browse Hadeth, use a digital Sebha (tasbih counter), and stream Quran radio stations. Built with Kotlin and Clean Architecture (data / domain / UI layers), MVVM, and Hilt for dependency injection.

> Package: `com.route.islamie_app101` · Min SDK 26 · Target/Compile SDK 36

---

## Features

### Quran
- Full Quran text bundled locally as per-surah `.txt` assets (`app/src/main/assets/quran/1.txt` … `114.txt`) — no network needed to read.
- Surah list screen (`SelectSuraFragment`) → tap a surah → verse-by-verse reading screen (`SuraFragment`), navigated via Safe Args with a `SuraDataModel` argument.
- **Recently read surahs**: the last-opened surahs are tracked and shown on the list screen, persisted through `SharedPreferences` (`RecentSurasDataSource`, `RecentSurasRepository`, `AddRecentSuraUseCase` / `GetRecentSurasUseCase`).
- Verse (aya) selection with a highlight state (`SetOnAyaClick`, `selected_aya_stroke.xml`).

### Hadeth (Hadith)
- Hadith collection bundled locally as a text asset (`app/src/main/assets/ahadeth/ahadeth.txt`).
- Browse list (`SelectHadethFragment`) → detail view (`HadethFragment`), passed via Safe Args (`HadethDataModel`).
- Card-style browsing UI via a `ViewPager`-based adapter (`HadethAdapter`).

### Sebha (Digital Tasbih)
- Tap-to-count dhikr counter (`SebhaFragment`) that cycles through *SubhanAllah*, *Alhamdulillah*, *Allahu Akbar* every 34 taps.
- Tap animation/rotation feedback on the bead graphic using `Handler`/`Runnable`.

### Quran Radio
- Live radio stations and reciter list fetched from the [mp3quran.net](https://mp3quran.net) public API (`GET /radios`, `GET /reciters`) via Retrofit.
- Two tabs in a `ViewPager2` (`RadioFragment`, `RadioPagerAdapter`): live **Radio** stations and **Reciters** (with per-reciter Moshaf/riwayah options).
- Background audio playback through a foreground **Media3 `MediaSessionService`** (`RadioService`) using ExoPlayer, with a system media notification (play/pause controls, notification icon).
- Retry/error handling UI (`ErrorFragment`, shown as a dialog destination) and a network-connectivity check (`NetworkConnectivity`) before hitting the API.

### General
- Custom splash screen using the AndroidX **SplashScreen API** (`installSplashScreen()` in `MainActivity`), with custom light/dark-friendly assets.
- Bottom navigation (`BottomNavigationView`) across the four main tabs — hidden automatically on the Sura and Hadeth detail screens for a full-screen reading experience.
- Custom Arabic font (`alfont_com_janna.ttf`) and RTL support (`android:supportsRtl="true"`), with an `values-ar/strings.xml` localization.

> **Note on the cover image**: the promotional cover shows a prayer-times screen (Fajr/Dhuhr/Asr/Maghrib/Isha countdown). That screen isn't present in this codebase — there's no prayer-times fragment, navigation destination, or API call for it anywhere in `app/src/main`, as it was cut from a design phase, it will be implemented in the near future.

---

## Architecture

The app follows **Clean Architecture** with a fairly strict three-layer split:

```
data/       → API clients, DTOs, mappers, repository implementations, local data sources
domain/     → repository interfaces, domain models, use cases (business logic)
ui/         → fragments, adapters, ViewModel, view-binding UI
```

- **Pattern**: MVVM — a single shared `IslamiViewModel` backs the UI layer, exposing `LiveData`.
- **DI**: Hilt (`@HiltAndroidApp` on `Application`, `@AndroidEntryPoint` on `MainActivity`/fragments), with `DataModule` and `NetworkModule` providing repositories and the Retrofit instance.
- **Use cases**: one class per action (`GetQuranUseCase`, `GetAhadethUseCase`, `GetRadioUseCase`, `GetReciterUseCase`, `AddRecentSuraUseCase`, `GetRecentSurasUseCase`), each calling into a repository interface implemented in the `data` layer.
- **Navigation**: single-Activity architecture — one `MainActivity` hosting a `NavHostFragment`, with the Navigation Component + **Safe Args** (Kotlin DSL) driving fragment-to-fragment navigation and typed argument passing.
- **Networking result wrapping**: `ApiResult` / `Resource` sealed wrappers for loading/success/error states surfaced to the UI.

---

## Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin 2.1.21 |
| Build | Gradle (Kotlin DSL), Android Gradle Plugin 8.7.3, version catalog (`libs.versions.toml`) |
| Architecture | Clean Architecture, MVVM |
| DI | Hilt (Dagger) 2.58, via KSP |
| Async | Kotlin Coroutines |
| Networking | Retrofit 3.0.0 + Gson converter |
| Serialization | kotlinx.serialization |
| Navigation | AndroidX Navigation Component + Safe Args |
| Media playback | Media3 (ExoPlayer, MediaSession, media3-ui) 1.11.0, running as a foreground `MediaSessionService` |
| UI | XML, View Binding, ConstraintLayout, Material Components, ViewPager2, RecyclerView |
| Splash screen | AndroidX Core SplashScreen API |
| Local persistence | SharedPreferences (recent surahs) + bundled text assets (Quran, Hadeth) |

---

## Project Structure

```
app/src/main/
├── assets/
│   ├── quran/              # 114 per-surah text files
│   └── ahadeth/ahadeth.txt
├── java/com/route/islamie_app101/
│   ├── Application.kt              # @HiltAndroidApp entry point
│   ├── MainActivity.kt             # single Activity, hosts NavHostFragment
│   ├── data/
│   │   ├── apis/                   # Retrofit interface + client (ApiManager, WebServices)
│   │   ├── data_models/            # (quran, ahadeth, radio, reciters)
│   │   ├── data_sources/           # local asset readers, recent-surahs prefs, radio remote source
│   │   ├── dependency_injection/   # Hilt modules
│   │   ├── mappers/                # domain model mappers
│   │   ├── repositorys/            # repository implementations
│   │   └── utils/                  # BASE_URL and other constants
│   ├── domain/
│   │   ├── data_models/            # domain models (Sura, Hadeth, Radio, Reciter, Moshaf)
│   │   ├── repository/             # repository interfaces
│   │   ├── use_cases/              # one use case per action
│   │   └── utils/                  # ApiResult wrapper
│   └── ui/
│       ├── IslamiViewModel.kt
│       ├── utils/Resource.kt
│       └── application_screens/
│           ├── quran_fragments/
│           ├── hadeth_fragments/
│           ├── sebha_fragment/
│           └── radio_fragments/    # includes services/RadioService (Media3 playback)
└── res/                             # layouts, drawables, nav_graph.xml, bottom_nav_menu.xml, fonts, strings (en + ar)
```

---

## API

The app talks to one external service, the [mp3quran.net API v3](https://www.mp3quran.net/ar/api):

```
Base URL: https://mp3quran.net/api/v3/

GET /radios     → list of live Quran radio stations
GET /reciters    → list of reciters and their Moshaf (riwayah/recitation) options
```

Everything else (Quran text, Hadeth text) ships bundled in `assets/` and is read from disk — there's no CMS or backend for that content.

---

## Permissions

Declared in `AndroidManifest.xml`:

- `INTERNET`, `ACCESS_NETWORK_STATE` — for the radio API and connectivity check
- `FOREGROUND_SERVICE`, `FOREGROUND_SERVICE_MEDIA_PLAYBACK` — for the background radio playback service
- `POST_NOTIFICATIONS` — for the media playback notification (Android 13+)

---

## Code Reference — every file, what it does, and why

This is a per-file breakdown of the entire `app/src/main/java` tree: what each class/interface is for, and why it exists in the architecture. It's organized the same way the code is (`data` → `domain` → `ui`), inside out from the dependency direction (domain depends on nothing; data and ui depend on domain).

> A note on scope: this documents every file, and every class/function within it, by purpose — not a literal per-line transcript.

This layer only knows about its own models and interfaces. Nothing here imports Retrofit, Context, or a View — that's the point of Clean Architecture: the core rules of the app don't care how data is fetched or displayed.

| File | Purpose |
|---|---|
| `domain/data_models/sura/SuraDataModel.kt` | The domain representation of a Quran surah: `id`, Arabic name, English name, verse count. `Serializable` so it can be passed as a Safe Args navigation argument between fragments. |
| `domain/data_models/hadeth/HadethDataModel.kt` | Domain representation of a single hadith: `title`, `content`, `id`. Also `Serializable` for the same navigation reason. |
| `domain/data_models/radio/RadioDataModel.kt` | A radio station: `name`, `id`, `url` (stream URL). Implements `DiffIdentifiable` so RecyclerView's `DiffUtil` can tell items apart by `id` without a full equality check. |
| `domain/data_models/radio/ReciterDataModel.kt` | A Quran reciter: `name`, `id`, and a list of their `MoshafDataModel` (recording sets). Also `DiffIdentifiable`. |
| `domain/data_models/radio/MoshafDataModel.kt` | One "Moshaf" (a specific recitation/riwayah recording set) belonging to a reciter: `server` (base audio URL), `surahList`, `rewayaId`. |
| `domain/data_models/radio/diff_util/DiffIdentifiable.kt` | A tiny interface (`val uniqueId: Int?`) that both `RadioDataModel` and `ReciterDataModel` implement, so a single generic `DiffItemCallback<T>` can diff either list without duplicating diff logic per type. |
| `domain/repository/quran_repository/QuranRepository.kt` | Interface: `getSurahsList(): List<SuraDataModel>`. The domain layer's contract for "give me the list of surahs" — implemented in `data/`, consumed via DI. |
| `domain/repository/quran_repository/recent_suras/RecentSuraReader.kt` | Interface: `getRecentSuras()`. Split from the writer interface below on purpose — see the ISP note under `RecentSurasRepository`. |
| `domain/repository/quran_repository/recent_suras/RecentSuraWriter.kt` | Interface: `addRecentSura(suras)`. |
| `domain/repository/quran_repository/recent_suras/RecentSurasRepository.kt` | `interface RecentSurasRepository: RecentSuraReader, RecentSuraWriter` — composes the two above into one repository contract. The split exists so a consumer that only needs to *read* recent surahs can depend on `RecentSuraReader` alone, without pulling in write access (Interface Segregation Principle) — though in practice every current use case just depends on the combined interface. |
| `domain/repository/hadeth_repository/AhadethRepository.kt` | Interface: `getAhadethList(): List<HadethDataModel>`. |
| `domain/repository/radio_repository/radio/RadioRepository.kt` | Interface: `suspend fun getRadiosList(): ApiResult<List<RadioDataModel>>`. Suspend + `ApiResult` because this one hits the network. |
| `domain/repository/radio_repository/reciters/RecitersRepository.kt` | Interface: `suspend fun getRecitersList(): ApiResult<List<ReciterDataModel>>`. |
| `domain/use_cases/quran_use_cases/GetQuranUseCase.kt` | `operator fun invoke()` wrapping `QuranRepository.getSurahsList()`. Using `operator fun invoke()` lets call sites read as `quranUseCase()` instead of `quranUseCase.getSurahsList()` — a common convention for single-responsibility use cases. |
| `domain/use_cases/quran_use_cases/GetRecentSurasUseCase.kt` | Wraps `RecentSurasRepository.getRecentSuras()`. |
| `domain/use_cases/quran_use_cases/AddRecentSuraUseCase.kt` | The one use case with actual logic: adds a surah to the front of the recent list, removes a duplicate if it's already there, and caps the list at `maxRecentItems = 5` by dropping the oldest entry. This is the only place that "recent surahs" business rule lives — the repository below it is a dumb read/write of whatever list it's given. |
| `domain/use_cases/ahadeth_use_cases/GetAhadethUseCase.kt` | Wraps `AhadethRepository.getAhadethList()`. |
| `domain/use_cases/radio_use_cases/GetRadioUseCase.kt` | Suspend wrapper around `RadioRepository.getRadiosList()`. |
| `domain/use_cases/radio_use_cases/GetReciterUseCase.kt` | Suspend wrapper around `RecitersRepository.getRecitersList()`. |
| `domain/utils/ApiResult.kt` | `sealed class ApiResult<T>` with `Success<T>(data)` / `Error<T>(errorMessage)`. The network-facing result type — used by the radio/reciter path from data source up through repository and use case. |

### `data/` — implements the domain contracts: network, local assets, SharedPreferences

| File | Purpose |
|---|---|
| `data/apis/WebServices.kt` | Retrofit interface with two endpoints: `GET radios` → `RadioResponse`, `GET reciters` → `ReciterResponse`. This is the entire network surface of the app. |
| `data/apis/ApiManager.kt` | A manual `object` that builds a `Retrofit` instance pointed at `BASE_URL` with the Gson converter, and exposes `getWebServices()`. **Effectively dead code** — `NetworkModule` (below) builds its own separate `Retrofit`/`WebServices` via Hilt `@Provides`, and everything in the app is injected through Hilt, not through `ApiManager`. Worth deleting or wiring one of the two away — right now there are two independent ways to construct the same Retrofit client. |
| `data/dependency_injection/NetworkModule.kt` | The Hilt module that's actually used: `@Provides` functions build the real `Retrofit` and `WebServices` singletons injected everywhere in the app via `@Inject` constructors. |
| `data/dependency_injection/DataModule.kt` | Hilt `@Binds` module mapping each repository *interface* (domain layer) to its concrete implementation (data layer) — e.g. `AhadethRepository → ImplAhadethRepository`. This is the file that wires Clean Architecture's layers together at compile time; without it Hilt wouldn't know which implementation to hand out when a use case asks for a repository interface. |
| `data/data_models/radio/RadioResponse.kt`, `RadiosItem.kt` | Gson DTOs matching the `/radios` JSON response shape exactly (field names via `@SerializedName`). Not used anywhere except as a deserialization target — mapped into domain models immediately after. |
| `data/data_models/reciters/ReciterResponse.kt`, `RecitersItem.kt`, `MoshafItem.kt` | Same idea for `/reciters` — raw JSON shape, including the nested `moshaf` list per reciter. |
| `data/mappers/RadioMapper.kt` | Converts a `RadiosItem` (network DTO) into a `RadioDataModel` (domain model), one item and one list at a time. This is the seam that keeps network JSON quirks out of the domain/UI layers. |
| `data/mappers/ReciterMapper.kt` | Same, for `RecitersItem → ReciterDataModel`, and internally calls `MoshafMapper` to convert the nested Moshaf list too. |
| `data/mappers/MoshafMapper.kt` | `MoshafItem → MoshafDataModel`. |
| `data/data_sources/radio/RadioDataSource.kt` | Thin wrapper around `WebServices`: calls `loadRadio()`/`loadReciters()` inside a try/catch and turns either a successful response or a thrown exception into an `ApiResult`. This is where raw network calls actually happen — nothing above this layer touches Retrofit directly. |
| `data/data_sources/quran/SurahDataSource.kt` | A **hardcoded `object`** holding three parallel lists — 114 Arabic surah names, 114 English surah names, 114 verse counts — zipped together at load time into the canonical `surahsList: List<SuraDataModel>`. This is static metadata (names/counts never change), so hardcoding avoids parsing a file for something that's fixed at compile time; the actual verse *text* is what's read from the `assets/quran/*.txt` files at read-time (in `SuraFragment`, not here). |
| `data/data_sources/quran/recent_suras/RecentSurasDataSource.kt` | Reads/writes the "recent surahs" list to `SharedPreferences` as JSON (via Gson serialize/deserialize of `List<SuraDataModel>`). Wraps deserialization in a try/catch returning an empty list on failure (e.g. if the stored JSON shape ever changes across app versions). |
| `data/data_sources/hadeth/HadethDataSource.kt` | Parses `assets/ahadeth/ahadeth.txt` line-by-line: it reads lines into a buffer until it hits a bare `#` delimiter, then treats the *first* buffered line as the hadith title and the rest joined by spaces as the content, assigning a sequential `id`. Caches the parsed list in memory (`isLoaded` flag) so the file is only read once per process. This is a custom, undocumented text format specific to this asset file — if the asset format ever changes, this parser breaks silently. |
| `data/repositorys/quran_repository/ImplQuranRepository.kt` | Implements `QuranRepository` by returning `SurahDataSource.surahsList` directly — no transformation needed since it's already domain-shaped. |
| `data/repositorys/quran_repository/recent_suras/ImplRecentSurasRepository.kt` | Implements both `RecentSuraReader` and `RecentSuraWriter` by delegating straight to `RecentSurasDataSource`. |
| `data/repositorys/ahadeth_repository/ImplAhadethRepository.kt` | Implements `AhadethRepository` by delegating to `HadethDataSource.ahadethList()`. |
| `data/repositorys/radio_repository/radio_tab/ImplRadioRepository.kt` | Implements `RadioRepository.getRadiosList()`: checks `NetworkConnectivity.isConnected()` first (returns a "Check the Internet Connection" `ApiResult.Error` immediately if offline, without attempting the call), then delegates to `RadioDataSource` and maps the result through `RadioMapper`. |
| `data/repositorys/radio_repository/reciters_tab/ImpRecitersRepository.kt` | Same pattern for reciters — connectivity check, then `RadioDataSource.loadRecitersSources()` (yes, reciters go through the *same* `RadioDataSource` class as radios — see note below), mapped through `ReciterMapper`. |
| `data/utils/Constants.kt` (in `data.utils`) | Two SharedPreferences keys: `MOST_RECENT_PREF` (the prefs file name) and `RECENT_SURAS_LIST` (the key within it). |
| `data/utils/NetworkConnectivity.kt` | Wraps `ConnectivityManager` to answer "is there validated internet right now" as a single `isConnected(): Boolean`, injected wherever a network call needs to fail fast instead of throwing. |

> **Naming note worth flagging**: `RadioDataSource` handles *both* `/radios` and `/reciters` calls (it has `loadRadioSources()` and `loadRecitersSources()`), and `ImpRecitersRepository` injects `RadioDataSource` under the name `recitersDataSource`. It works, but the class name doesn't reflect that it's shared — a reader skimming just `ImpRecitersRepository`'s imports could reasonably assume a separate reciters-specific data source exists. Renaming it to something like `RadioApiDataSource` would remove the ambiguity.

### `ui/` — fragments, adapters, and the single shared ViewModel

| File | Purpose |
|---|---|
| `ui/IslamiViewModel.kt` | The **one ViewModel for the whole app** (Hilt-scoped per fragment via `by viewModels()`, but stateless enough that every screen effectively shares the same use-case wiring). Exposes `surasList` and `ahadethList` as plain properties (loaded eagerly at construction, since they're local/instant), and `radioState`/`recitersState` as `LiveData<Resource<...>>` for the async network calls, triggered by `loadRadioList()`/`loadRecitersList()`. Also proxies `loadRecentSuras()`/`addRecentSura()` to their use cases. |
| `ui/utils/Resource.kt` | `sealed class Resource<T>`: `Loading`/`Success(data)`/`Error(errorMessage)` — the UI-facing state wrapper (distinct from `domain/utils/ApiResult`, which is the *data-layer* result type). The ViewModel converts one into the other inside `loadRadioList()`/`loadRecitersList()`. |
| `ui/application_screens/quran_fragments/SelectSuraFragment.kt` | The Quran tab's landing screen: shows the full surah list and a "most recent" horizontal list. Wires click listeners on both adapters to navigate to `SuraFragment` and record the tap via `addRecentSura()`. |
| `ui/application_screens/quran_fragments/SuraFragment.kt` | The verse-reading screen for one surah. Reads `assets/quran/{id}.txt` line-by-line directly off disk (skipping blank lines, numbering each non-blank line as an aya) — this is the one place actual Quran verse text is loaded, separate from the static metadata in `SurahDataSource`. Also owns tap-to-highlight-a-verse state via `selectedAyaPosition`, re-styling the previously selected and newly selected items on each tap. |
| `ui/application_screens/quran_fragments/sura_recycler_view_adapter/SelectSuraRecyclerViewAdapter.kt` | Plain `RecyclerView.Adapter` binding the full 114-surah list. |
| `ui/application_screens/quran_fragments/sura_recycler_view_adapter/RecentSuraRecyclerViewAdapter.kt` | A `ListAdapter` (uses `DiffUtil` via `RecentDiffUtil`) for the "most recent" strip — `ListAdapter` here specifically so `submitList()` handles the add/reorder/cap-at-5 diffing automatically instead of manual `notifyDataSetChanged()`. |
| `ui/application_screens/quran_fragments/sura_recycler_view_adapter/SuraRecyclerViewAdapter.kt` | Binds the list of aya (verse) strings inside `SuraFragment`. |
| `ui/application_screens/quran_fragments/diff_util/RecentDiffUtil.kt` | `DiffUtil.ItemCallback<SuraDataModel>` comparing by `id` for item identity, and all four fields for content equality. |
| `ui/application_screens/quran_fragments/interfaces/SetOnSuraClick.kt`, `SetOnRecentSuraClick.kt`, `SetOnAyaClick.kt` | Small click-callback interfaces (the last two are Kotlin `fun interface`s / SAM conversions) decoupling adapters from the fragments that use them — standard adapter-callback pattern so the adapter doesn't need a reference to the fragment or NavController directly. |
| `ui/application_screens/hadeth_fragments/SelectHadethFragment.kt` | Hadeth landing screen: a `ViewPager2` card carousel (`HadethAdapter`) with a custom `CompositePageTransformer` that scales side pages down (0.85–1.0 based on distance from center) for a "peeking cards" visual effect. Tapping a card navigates to `HadethFragment` with the selected hadith passed via Safe Args. |
| `ui/application_screens/hadeth_fragments/HadethFragment.kt` | Hadith detail/reading screen — just binds the passed-in `HadethDataModel`'s title/content/id and handles the back button. |
| `ui/application_screens/hadeth_fragments/hadeth_view_pager/HadethAdapter.kt` | `RecyclerView.Adapter` for the ViewPager2 cards. |
| `ui/application_screens/hadeth_fragments/hadeth_view_pager/SetOnHadethClick.kt` | Click callback interface, same pattern as the Quran side. |
| `ui/application_screens/sebha_fragment/SebhaFragment.kt` | The tasbih counter. Each tap increments a count, swaps in an "effect" drawable then reverts it plus a 30° rotation after 200ms (via `Handler.postDelayed`, not an `Animator` — a simplification worth noting if this gets touched again, since `Handler`-based timing can drift under load while `ViewPropertyAnimator`/`ObjectAnimator` callbacks wouldn't), and cycles through the three dhikr phrases every 34 taps (34 being the traditional count per phrase in this dhikr sequence). The phrase list is hardcoded inline with a `//Todo make it combatable with clean code` left by the developer — i.e. this fragment is a known/flagged exception to the Clean Architecture split used everywhere else (no repository/use case backs it; it's pure UI state). |
| `ui/application_screens/radio_fragments/RadioFragment.kt` | The largest and most stateful screen in the app. Hosts a `ViewPager2` with two pages (Radio stations / Reciters) linked to a `TabLayout` via `TabLayoutMediator`; lazily triggers `loadRadioList()`/`loadRecitersList()` only when each tab is first viewed. Owns playback UI state directly in the fragment: which item is selected (`selectedRadioPosition`), which items are muted (`mutedPositions`, a `Set<Int>` — so mute state is per-list-position, not tied to a specific station), and toggles play/pause/switch-station logic by connecting to `RadioService` through a `MediaController`. On error, navigates to `ErrorFragment` as a dialog and listens for its dismissal via a `FragmentResultListener` (`RADIO_RETRY`) to retry the failed tab specifically (0 = radios, 1 = reciters). |
| `ui/application_screens/radio_fragments/radio_adapter/RadioItemAdapter.kt` | A **generic** `ListAdapter<T : DiffIdentifiable>` reused for both the radio list and the reciter list — it takes its bind logic as a constructor lambda (`bind: (RadioItemBinding, T?, Int) -> Unit`) rather than overriding `onBindViewHolder` per type, which is why `RadioFragment` can instantiate one for `RadioDataModel` and another for `ReciterDataModel` from the same adapter class. |
| `ui/application_screens/radio_fragments/radio_adapter/RadioPagerAdapter.kt` | The ViewPager2 adapter with exactly 2 pages, handing out the radio adapter or the reciters adapter depending on position. |
| `ui/application_screens/radio_fragments/radio_adapter/diff_util/DiffItemCallback.kt` | Generic `DiffUtil.ItemCallback<T : DiffIdentifiable>` — identity by `uniqueId`, content by full equality. Pairs with `RadioItemAdapter` to make one diffing implementation work for both data types. |
| `ui/application_screens/radio_fragments/services/RadioService.kt` | A `MediaSessionService` (Media3) — the actual foreground service declared in the manifest. Creates one `ExoPlayer` and one `MediaSession` in `onCreate()`, exposes the session via `onGetSession()` so `RadioFragment`'s `MediaController` can bind to it and drive playback (`setMediaItem`, `prepare`, `play`, `volume`), and releases both in `onDestroy()`. This is what keeps audio playing when the app is backgrounded, and what produces the system media notification. |
| `ui/application_screens/radio_fragments/services/NotifificationUtils.kt` *(sic — typo in the actual filename)* | `createThemedMetadata(title)`: builds a `MediaMetadata` with a solid gold-colored (`#E2BE7F`) placeholder bitmap as the notification artwork, since stations don't have per-station cover art. `bitmapToByteArray` is a small helper for that. |
| `ui/application_screens/radio_fragments/ErrorFragment.kt` | A `DialogFragment` shown over `RadioFragment` on network/API failure — transparent background, shows the error message, and on dismiss (whether via the Retry button or swipe-away) emits a `RADIO_RETRY` fragment result carrying which tab failed, which `RadioFragment` listens for to retry. |
| `utils/Constants.kt` (top-level, in `com.route.islamie_app101.utils`, distinct from `data/utils/Constants.kt`) | `AHADETH_PATH`, `SURA_PATH`, `BASE_URL = "https://mp3quran.net/api/v3/"`, `TAB_NUM`, `RADIO_RETRY` — the app-wide constants used across data source and UI layers. **Two different `Constants` classes exist** (`data.utils.Constants` for the SharedPreferences keys, `utils.Constants` for everything else) — functionally fine since they're in different packages, but easy to import the wrong one by accident; worth merging into one file if this codebase grows. |
| `Application.kt` | `@HiltAndroidApp` — the Hilt entry point that generates the app's dependency graph. Contains nothing else. |
| `MainActivity.kt` | The single Activity. Installs the splash screen, sets up edge-to-edge (`WindowCompat.setDecorFitsSystemWindows(window, false)`), hosts the `NavHostFragment`, wires the bottom nav to the `NavController`, and hides the bottom nav specifically on `suraFragment` and `hadethFragment` (the two full-screen reading destinations). |

---

## Getting Started

### Prerequisites
- Android Studio (recent version supporting AGP 8.7.3 / compileSdk 36)
- JDK 17

### Build & Run
```bash
git clone https://github.com/youssef-shokry/islamiApp.git
cd islamiApp
./gradlew assembleDebug
```
Or open the project in Android Studio and run the `app` configuration on a device/emulator running API 26+.

No API keys or `local.properties` secrets are required — the only network dependency is the public mp3quran.net API.

---

## Credits
Made by Youssef Shokry.

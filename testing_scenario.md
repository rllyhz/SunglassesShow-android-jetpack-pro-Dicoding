**Testing Scenario**

**Unit Testing**
- **SunGlassesShowRepositoryTest**
    - Melakukan fetching movies list, memastikan data tersebut tidak null dan memiliki jumlah item sesuai yang diharapkan.
    - Melakukan fetching tv shows list, memastikan data tersebut tidak null dan memiliki jumlah item sesuai yang diharapkan.
    - Melakukan fetching detail dari movie berdasarkan id yang diberikan, memastikan data tersebut tidak null dan memiliki nilai properti sesuai yang diharapkan.
    - Melakukan fetching detail dari tv show berdasarkan id yang diberikan, memastikan data tersebut tidak null dan memiliki nilai properti sesuai yang diharapkan.
    - Melakukan fetching similar content dari movie berdasarkan id yang diberikan, memastikan data tersebut tidak null dan memiliki jumlah item sesuai yang diharapkan.
    - Melakukan fetching similar content dari tv show berdasarkan id yang diberikan, memastikan data tersebut tidak null dan memiliki jumlah item sesuai yang diharapkan.
    - Memuat semua data favorite movies dan memastikan data tersebut tidak null.
    - Memuat semua data favorite tv shows dan memastikan data tersebut tidak null.
    - Melakukan pengujian pada fungsi addFavMovie dan deleteFavMovie dan memastikan fungsi tersebut mengembalikan nilai sesuai yang diharapkan.
    - Melakukan pengujian pada fungsi addFavTVShow dan deleteFavTVShow dan memastikan fungsi tersebut mengembalikan nilai sesuai yang diharapkan.
    - Memuat data favorite movie berdasarkan id yang diberikan dan memastikan data tersebut tidak null.
    - Memuat data favorite tv show berdasarkan id yang diberikan dan memastikan data tersebut tidak null.

- **MainViewModelTest**
    - Memanipulasi data ketika pemanggilan data movies di kelas SunGlassesShowRepository.
    - Memastikan metode di kelas SunGlassesShowRepository terpanggil.
    - Memastikan masing-masing data Movies dan TV Shows tidak null.
    - Memastikan jumlah data masing-masing Movies dan TV Shows telah sesuai seperti yang diharapkan.
    
- **DetailViewModelTest**
    - Memanipulasi data ketika pemanggilan data tvShows di kelas SunGlassesShowRepository.
    - Memastikan metode di kelas SunGlassesShowRepository terpanggil.
    - Memuat data list movies yang kemungkinan serupa (similar) dengan data Movie yang sedang aktif.
    - Memuat data list tvShows yang kemungkinan serupa (similar) dengan data TVShow yang sedang aktif.
    - Memastikan jumlah data masing-masing data list movies dan tvShows yang _similar_ sesuai ekspektasi.
    - Melakukan tambah dan hapus data favorites pada movie dan kembalian data tidak berupa null.
    - Melakukan tambah dan hapus data favorites pada tv show dan kembalian data tidak berupa null.
   
- **FavoritesViewModel**
    - Memuat data list favorite movies dan memastikan data tidak null
    - Memuat data list favorite tv shows dan memastikan data tidak null

**Instrumental Testing**
- **MainActivityTest**
    - Memuat dan menampilkan data movies pada recyclerview dan memastikan setiap item berfungsi ketika di-click.
    - Mendemonstrasikan swiping action pada tab layout dan memastikan recyclerview dari data tv shows muncul.
    - Memuat dan menampilkan data tv shows pada recyclerview dan memastikan setiap item berfungsi ketika di-click.
    
- **DetailActivityTest**
    - Mendemonstrasikan detail activity dan memastikan semua komponen yang ada tampil dan berfungsi dengan baik.
    - Mendemonstrasikan detail activity dan memastikan semua komponen textview yang ada menampilkan text yang diharapkan.
    - Mendemonstrasikan detail activity dan memastikan data Similar Movies pada recyclerview tampil.
    - Memastikan setiap item didalam recyclerview berfungsi ketika di-klik dan memiliki jumlah item yang sesuai dengan dummy data.
    - Melakukan simulasi klik pada tombol toggle_btn_fav untuk menambahkan dan menghapus movie item dari favorite dan cek jika recyclerview pada FavoriteActivity muncul.

**Testing Scenario**

**Unit Testing**

- **MainViewModelTest**
    - Memuat semua data json Movies dan TV Shows yang ada pada DataGenerator object.
    - Memastikan masing-masing data Movies dan TV Shows tidak null.
    - Memastikan jumlah data masing-masing Movies dan TV Shows telah sesuai seperti yang diharapkan.
- **DetailViewModelTest**
    - Memuat data Movies dan TV Shows masing-masing satu item dan memastikan tidak bernilai null.
    - Memuat dummy data yang kemungkinan serupa (similar) dengan data currentMovie.
    - Memuat dummy data yang kemungkinan serupa (similar) dengan data currentTvShow.
    - Memastikan jumlah data masing-masing data dummy yang _similar_ sesuai ekspektasi.

**Instrumental Testing**
    - memuat dan menampilkan data movies pada recyclerview dan memastikan setiap item berfungsi ketika di-click.
    - mendemonstrasikan swiping action pada tab layout dan memastikan recyclerview dari data tv shows muncul.
    - memuat dan menampilkan data tv shows pada recyclerview dan memastikan setiap item berfungsi ketika di-click.
    - mendemonstrasikan detail activity dan memastikan semua komponen yang ada tampil dan berfungsi dengan baik.
    - mendemonstrasikan detail activity dan memastikan semua komponen textview yang ada menampilkan text yang diharapkan.

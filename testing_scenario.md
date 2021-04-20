**Testing Scenario**

**Unit Testing**

- **MainViewModelTest**
    - Memuat semua data json Movies dan TV Shows yang ada pada DataGenerator object
    - Memastikan masing-masing data Movies dan TV Shows tidak null
    - Memastikan jumlah data masing-masing Movies dan TV Shows telah sesuai seperti yang diharapkan
- **DetailViewModelTest**
    - Memuat data Movies dan TV Shows masing-masing satu item dan memastikan tidak bernilai null
    - Memuat dummy data yang kemungkinan serupa (similar) dengan data currentMovie
    - Memuat dummy data yang kemungkinan serupa (similar) dengan data currentTvShow
    - Memastikan jumlah data masing-masing data dummy yang _similar_ sesuai ekspektasi

**Instrumental Testing**

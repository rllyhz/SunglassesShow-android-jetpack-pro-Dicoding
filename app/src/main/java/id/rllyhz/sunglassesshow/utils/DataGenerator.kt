package id.rllyhz.sunglassesshow.utils

import android.content.Context
import com.google.gson.Gson
import id.rllyhz.sunglassesshow.data.Movie
import id.rllyhz.sunglassesshow.data.TVShow
import java.io.IOException

object DataGenerator {
    private const val MOVIES_FILENAME = "movies.json"
    private const val TV_SHOW_FILENAME = "tv_show.json"

    private fun loadAssetFile(context: Context, filename: String): String? {
        val jsonString: String

        try {
            jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return jsonString
    }

    fun getAllMovies(context: Context): Array<Movie>? {
        loadAssetFile(context, MOVIES_FILENAME)?.run {
            return Gson().fromJson(this, Array<Movie>::class.java)
        }

        return null
    }

    fun getAllTVShows(context: Context): Array<TVShow>? {
        loadAssetFile(context, TV_SHOW_FILENAME)?.run {
            return Gson().fromJson(this, Array<TVShow>::class.java)
        }

        return null
    }

    fun getAllMovies(): Array<Movie> =
        Gson().fromJson(movieJsonString, Array<Movie>::class.java)

    fun getAllTVShows(): Array<TVShow> =
        Gson().fromJson(tvShowJsonString, Array<TVShow>::class.java)

    private val movieJsonString = """
        [
          {
            "id": 0,
            "poster_url": "poster_a_start_is_born.jpg",
            "title": "A Star Is Born",
            "year": 2018,
            "genres": "Drama, Romance, Music",
            "duration": "2h 16m",
            "rate": "R",
            "released_at": "October 3, 2018",
            "language": "US",
            "user_score": "75%",
            "tagline": "-",
            "synopsis": "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "director": "Bradley Cooper"
          },
          {
            "id": 1,
            "poster_url": "poster_alita.jpg",
            "title": "Alita: Battle Angel",
            "year": 2019,
            "genres": "Action, Science Fiction, Adventure",
            "duration": "2h 2m",
            "rate": "PG-13",
            "released_at": "January 31, 2019",
            "language": "US",
            "user_score": "72%",
            "tagline": "An angel falls. A warrior rises.",
            "synopsis": "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "director": "Robert Rodriguez"
          },
          {
            "id": 2,
            "poster_url": "poster_aquaman.jpg",
            "title": "Aquaman",
            "year": 2018,
            "genres": "Action, Adventure, Fantasy",
            "duration": "2h 23m",
            "rate": "PG-13",
            "released_at": "December 7, 2018",
            "language": "US",
            "user_score": "69%",
            "tagline": "Home Is Calling",
            "synopsis": "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "director": "James Wan"
          },
          {
            "id": 3,
            "poster_url": "poster_bohemian.jpg",
            "title": "Bohemian Rhapsody",
            "year": 2018,
            "genres": "Music, Drama, History",
            "duration": "2h 23m",
            "rate": "PG-13",
            "released_at": "October 24, 2018",
            "language": "US",
            "user_score": "80%",
            "tagline": "Fearless lives forever",
            "synopsis": "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "director": "Bryan Singer"
          },
          {
            "id": 4,
            "poster_url": "poster_cold_pursuit.jpg",
            "title": "Cold Pursuit",
            "year": 2019,
            "genres": "Action, Crime, Thriller",
            "duration": "1h 59m",
            "rate": "PG-13",
            "released_at": "February 7, 2019",
            "language": "US",
            "user_score": "57%",
            "tagline": "-",
            "synopsis": "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "director": "Hans Petter Moland"
          },
          {
            "id": 5,
            "poster_url": "poster_creed.jpg",
            "title": "Creed II",
            "year": 2018,
            "genres": "Drama",
            "duration": "2h 10m",
            "rate": "PG-13",
            "released_at": "November 21, 2018",
            "language": "US",
            "user_score": "69%",
            "tagline": "There's More to Lose than a Title",
            "synopsis": "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "director": "Steven Caple Jr."
          },
          {
            "id": 6,
            "poster_url": "poster_crimes.jpg",
            "title": "Fantastic Beast: The Crime of Grindelwald",
            "year": 2018,
            "genres": "Adventure, Fantasy, Drama",
            "duration": "2h 14m",
            "rate": "PG-13",
            "released_at": "November 14, 2018",
            "language": "US",
            "user_score": "69%",
            "tagline": "Fate of One. Future of All.",
            "synopsis": "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
            "director": "David Yates"
          },
          {
            "id": 7,
            "poster_url": "poster_glass.jpg",
            "title": "Glass",
            "year": 2019,
            "genres": "Thriller, Drama, Science Fiction",
            "duration": "2h 9m",
            "rate": "PG-13",
            "released_at": "January 16, 2019",
            "language": "US",
            "user_score": "67%",
            "tagline": "You Cannot Contain What You Are",
            "synopsis": "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            "director": "M. Night Shyamalan"
          },
          {
            "id": 8,
            "poster_url": "poster_how_to_train.jpg",
            "title": "How to Train Your Dragon",
            "year": 2010,
            "genres": "Fantasy, Adventure, Animation, Family",
            "duration": "1h 38m",
            "rate": "PG",
            "released_at": "March 10, 2010",
            "language": "US",
            "user_score": "78%",
            "tagline": "One adventure will change two worlds",
            "synopsis": "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father.",
            "director": "Dean DeBlois"
          },
          {
            "id": 9,
            "poster_url": "poster_infinity_war.jpg",
            "title": "Avengers: Infinity War",
            "year": 2018,
            "genres": "Adventure, Action, Science Fiction",
            "duration": "2h 29m",
            "rate": "PG",
            "released_at": "April 25, 2018",
            "language": "US",
            "user_score": "83%",
            "tagline": "An entire universe. Once and for all.",
            "synopsis": "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            "director": "Joe Russo"
          },
          {
            "id": 10,
            "poster_url": "poster_master_z.jpg",
            "title": "Master Z: Ip Man Legacy",
            "year": 2018,
            "genres": "Action",
            "duration": "1h 47m",
            "rate": "PG-13",
            "released_at": "December 20, 2018",
            "language": "TW",
            "user_score": "59%",
            "tagline": "-",
            "synopsis": "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
            "director": "Yuen Woo-ping"
          },
          {
            "id": 11,
            "poster_url": "poster_mortal_engines.jpg",
            "title": "Mortal Engines",
            "year": 2018,
            "genres": "Adventure, Science Fiction",
            "duration": "2h 9m",
            "rate": "PG-13",
            "released_at": "November 27, 2018",
            "language": "US",
            "user_score": "61%",
            "tagline": "Some scars never heal",
            "synopsis": "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
            "director": "Christian Rivers"
          },
          {
            "id": 12,
            "poster_url": "poster_overlord.jpg",
            "title": "Overlord",
            "year": 2018,
            "genres": "Horror, War, Science Fiction",
            "duration": "1h 50m",
            "rate": "R",
            "released_at": "November 1, 2018",
            "language": "US",
            "user_score": "61%",
            "tagline": "Stop the unstoppable",
            "synopsis": "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
            "director": "Julius Avery"
          },
          {
            "id": 13,
            "poster_url": "poster_ralph.jpg",
            "title": "Ralph Breaks the Internet",
            "year": 2018,
            "genres": "Family, Animation, Comedy, Adventure",
            "duration": "1h 52m",
            "rate": "PG",
            "released_at": "November 20, 2018",
            "language": "US",
            "user_score": "72%",
            "tagline": "Who Broke the Internet?",
            "synopsis": "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            "director": "Phil Johnston"
          },
          {
            "id": 14,
            "poster_url": "poster_robin_hood.jpg",
            "title": "Robin Hood",
            "year": 2018,
            "genres": "Adventure, Action, Thriller",
            "duration": "1h 56m",
            "rate": "PG-13",
            "released_at": "November 21, 2018",
            "language": "US",
            "user_score": "59%",
            "tagline": "The legend you know. The story you don't.",
            "synopsis": "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            "director": "Otto Bathurst"
          },
          {
            "id": 15,
            "poster_url": "poster_spiderman.jpg",
            "title": "Spider-Man: Into the Spider-Verse",
            "year": 2018,
            "genres": "Action, Adventure, Animation, Science Fiction, Comedy",
            "duration": "1h 57m",
            "rate": "PG",
            "released_at": "December 6, 2018",
            "language": "US",
            "user_score": "84%",
            "tagline": "More Than One Wears the Mask.",
            "synopsis": "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson 'Kingpin' Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "director": "Rodney Rothman"
          },
          {
            "id": 16,
            "poster_url": "poster_t34.jpg",
            "title": "T-34",
            "year": 2018,
            "genres": "War, Action, Drama, History",
            "duration": "2h 19m",
            "rate": "PG",
            "released_at": "December 27, 2018",
            "language": "RU",
            "user_score": "64%",
            "tagline": "Fast And Furious On Tanks",
            "synopsis": "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
            "director": "Alexey Sidorov"
          }
        ]
    """.trimIndent()

    private val tvShowJsonString = """
        [
          {
            "id": 0,
            "poster_url": "poster_arrow.jpg",
            "title": "Arrow",
            "year": 2012,
            "genres": "Crime, Drama, Mystery, Action & Adventure",
            "duration": "42m",
            "rate": "TV-14",
            "released_at": "October 10, 2012",
            "language": "US",
            "user_score": "66%",
            "tagline": "Heroes fall. Legends rise.",
            "synopsis": "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "creator": "Greg Berlanti"
          },
          {
            "id": 1,
            "poster_url": "poster_arrow.jpg",
            "title": "Doom Patrol",
            "year": 2019,
            "genres": "Sci-Fi & Fantasy, Comedy, Drama",
            "duration": "49m",
            "rate": "TV-MA",
            "released_at": "February 15, 2019",
            "language": "US",
            "user_score": "76%",
            "tagline": "-",
            "synopsis": "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "creator": "Jeremy Carver"
          },
          {
            "id": 2,
            "poster_url": "poster_dragon_ball.jpg",
            "title": "Dragon Ball Z",
            "year": 1989,
            "genres": "Animation, Sci-Fi & Fantasy, Action & Adventure",
            "duration": "26m",
            "rate": "TV-PG",
            "released_at": "April 26, 1989",
            "language": "US",
            "user_score": "82%",
            "tagline": "-",
            "synopsis": "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
            "creator": "Akira Toriyama"
          },
          {
            "id": 3,
            "poster_url": "poster_fairytail.jpg",
            "title": "Fairy Tail: Phoenix Priestess",
            "year": 2012,
            "genres": "Action, Adventure, Comedy, Fantasy, Animation",
            "duration": "1h 26m",
            "rate": "PG-13",
            "released_at": "August 18, 2012",
            "language": "US",
            "user_score": "73%",
            "tagline": "-",
            "synopsis": "The film revolves around a mysterious girl named Éclair who appears before Fairy Tail, the world's most notorious wizard's guild. She lost all of her memories, except for the imperative that she must deliver two Phoenix Stones somewhere. The stones may spell the collapse of the magical world, and Natsu, Lucy, and the rest of the Fairy Tail guild are caught up in the intrigue.",
            "creator": "Masaya Fujimori"
          },
          {
            "id": 4,
            "poster_url": "poster_family_guy.jpg",
            "title": "Family Guy",
            "year": 1999,
            "genres": "Animation, Comedy",
            "duration": "22m",
            "rate": "TV-14",
            "released_at": "January 31, 1999",
            "language": "US",
            "user_score": "70%",
            "tagline": "Parental Discretion Advised, that's how you know it's good",
            "synopsis": "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            "creator": "Seth MacFarlane"
          },
          {
            "id": 5,
            "poster_url": "poster_flash.jpg",
            "title": "The Flash",
            "year": 2014,
            "genres": "Drama, Sci-Fi & Fantasy",
            "duration": "44m",
            "rate": "TV-14",
            "released_at": "October 7, 2014",
            "language": "US",
            "user_score": "77%",
            "tagline": "The fastest man alive.",
            "synopsis": "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only 'meta-human' who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "creator": "Greg Berlanti"
          },
          {
            "id": 6,
            "poster_url": "poster_gotham.jpg",
            "title": "Gotham",
            "year": 2014,
            "genres": "Drama, Crime, Sci-Fi & Fantasy",
            "duration": "43m",
            "rate": "TV-14",
            "released_at": "September 22, 2014",
            "language": "US",
            "user_score": "75%",
            "tagline": "Before Batman, there was Gotham.",
            "synopsis": "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "creator": "Bruno Heller"
          },
          {
            "id": 7,
            "poster_url": "poster_grey_anatomy.jpg",
            "title": "Grey's Anatomy",
            "year": 2005,
            "genres": "Drama",
            "duration": "43m",
            "rate": "TV-14",
            "released_at": "March 27, 2005",
            "language": "US",
            "user_score": "82%",
            "tagline": "The life you save may be your own.",
            "synopsis": "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "creator": "Shonda Rhimes"
          },
          {
            "id": 8,
            "poster_url": "poster_hanna.jpg",
            "title": "Hanna",
            "year": 2019,
            "genres": "Action & Adventure, Drama",
            "duration": "50m",
            "rate": "TV-14",
            "released_at": "March 28, 2019",
            "language": "US",
            "user_score": "75%",
            "tagline": "The life you save may be your own.",
            "synopsis": "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            "creator": "David Farr"
          },
          {
            "id": 9,
            "poster_url": "poster_iron_fist.jpg",
            "title": "Marvel's Iron Fist",
            "year": 2017,
            "genres": "Action & Adventure, Drama, Sci-Fi & Fantasy",
            "duration": "55m",
            "rate": "TV-MA",
            "released_at": "March 17, 2017",
            "language": "US",
            "user_score": "66%",
            "tagline": "-",
            "synopsis": "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            "creator": "Scott Buck"
          },
          {
            "id": 10,
            "poster_url": "poster_naruto_shipudden.jpg",
            "title": "Naruto Shippūden",
            "year": 2007,
            "genres": "Animation, Action & Adventure, Sci-Fi & Fantasy",
            "duration": "25m",
            "rate": "TV-14",
            "released_at": "February 15, 2007",
            "language": "US",
            "user_score": "86%",
            "tagline": "-",
            "synopsis": "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
            "creator": "Masashi Kishimoto"
          },
          {
            "id": 11,
            "poster_url": "poster_ncis.jpg",
            "title": "NCIS",
            "year": 2003,
            "genres": "Crime, Action & Adventure, Drama",
            "duration": "45m",
            "rate": "TV-14",
            "released_at": "September 23, 2003",
            "language": "US",
            "user_score": "74%",
            "tagline": "-",
            "synopsis": "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            "creator": "Donald P. Bellisario"
          },
          {
            "id": 12,
            "poster_url": "poster_ncis.jpg",
            "title": "Riverdale",
            "year": 2017,
            "genres": "Mystery, Drama, Crime",
            "duration": "45m",
            "rate": "TV-14",
            "released_at": "January 26, 2017",
            "language": "US",
            "user_score": "86%",
            "tagline": "Small town. Big secrets.",
            "synopsis": "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "creator": "Roberto Aguirre-Sacasa"
          },
          {
            "id": 13,
            "poster_url": "poster_shameless.jpg",
            "title": "Shameless",
            "year": 2011,
            "genres": "Drama, Comedy",
            "duration": "57m",
            "rate": "TV-MA",
            "released_at": "January 9, 2011",
            "language": "US",
            "user_score": "86%",
            "tagline": "The Gallaghers. Absolutely, Wildly, Unapologetically... Shameless",
            "synopsis": "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
            "creator": "John Wells"
          },
          {
            "id": 14,
            "poster_url": "poster_supergirl.jpg",
            "title": "Supergirl",
            "year": 2015,
            "genres": "Drama, Sci-Fi & Fantasy, Action & Adventure",
            "duration": "42m",
            "rate": "TV-MA",
            "released_at": "October 26, 2015",
            "language": "US",
            "user_score": "72%",
            "tagline": "A force against fear",
            "synopsis": "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
            "creator": "Greg Berlanti"
          },
          {
            "id": 15,
            "poster_url": "poster_supernatural.jpg",
            "title": "Supernatural",
            "year": 2005,
            "genres": "Drama, Mystery, Sci-Fi & Fantasy",
            "duration": "45m",
            "rate": "TV-14",
            "released_at": "September 13, 2005",
            "language": "US",
            "user_score": "82%",
            "tagline": "Between darkness and deliverance",
            "synopsis": "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
            "creator": "Eric Kripke"
          },
          {
            "id": 16,
            "poster_url": "poster_the_simpson.jpg",
            "title": "The Simpsons",
            "year": 1989,
            "genres": "Family, Animation, Comedy",
            "duration": "22m",
            "rate": "TV-PG",
            "released_at": "December 17, 1989",
            "language": "US",
            "user_score": "78%",
            "tagline": "On your marks, get set, d'oh!",
            "synopsis": "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
            "creator": "Matt Groening"
          },
          {
            "id": 17,
            "poster_url": "poster_the_umbrella.jpg",
            "title": "The Umbrella Academy",
            "year": 2019,
            "genres": "Action & Adventure, Sci-Fi & Fantasy, Drama",
            "duration": "55m",
            "rate": "TV-MA",
            "released_at": "February 15, 2019",
            "language": "US",
            "user_score": "87%",
            "tagline": "Super. Dysfunctional. Family.",
            "synopsis": "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
            "creator": "Steve Blackman"
          },
          {
            "id": 18,
            "poster_url": "poster_the_walking_dead.jpg",
            "title": "The Walking Dead",
            "year": 2010,
            "genres": "Action & Adventure, Drama, Sci-Fi & Fantasy",
            "duration": "42m",
            "rate": "TV-MA",
            "released_at": "October 31, 2010",
            "language": "US",
            "user_score": "81%",
            "tagline": "Fight the dead. Fear the living.",
            "synopsis": "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
            "creator": "Frank Darabont"
          }
        ]
    """.trimIndent()
}
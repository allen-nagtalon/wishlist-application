import { Box, Button, Container, Typography } from '@mui/material'
import AddIcon from '@mui/icons-material/Add'
import { useEffect, useState } from 'react'
import WishCardList from '../components/WishCardList/WishCardList'
import { useParams } from 'react-router-dom'

const testWishes = [
  {
    title: 'XG OFFICIAL LIGHT STICK Ver.1（w/XG Trading Card / Set of 7)',
    desc: 'Finally, the official XG light stick is now available! With a design reminiscent of a spaceship and featuring a trigger-type button, it can switch between seven member colours and auto colour change in normal performance mode.',
    url: 'https://shop.xgalx.com/collections/all-item/products/xglx-0100',
    photoUrl: 'https://shop.xgalx.com/cdn/shop/files/XGLX-0100_t_01.jpg?v=1714458221'
  },
  {
    title: 'The Legend of Zelda™: Echoes of Wisdom',
    desc: 'The Legend of Zelda: Echoes of Wisdom is an upcoming video game in The Legend of Zelda series published by Nintendo for the Nintendo Switch.',
    url: 'https://www.nintendo.com/us/store/products/the-legend-of-zelda-echoes-of-wisdom-119253/',
    photoUrl: 'https://assets.nintendo.com/image/upload/ar_16:9,c_lpad,w_1240/b_white/f_auto/q_auto/ncom/software/switch/70010000084618/e3a49cf14180cec09c515595fb7121b048a0f8327eae2d248909d0f0c64a9dc9'
  },
  {
    title: 'Apari Bunny Twins Skate Deck',
    desc: 'Skate deck from artist @ApariArt.',
    url: 'https://www.apari-shop.com/products/bunny-twins-skate-deck',
    photoUrl: 'https://www.apari-shop.com/cdn/shop/files/bunnycloseup.jpg?v=1684531571&width=990'
  },
  {
    title: 'Uniqlo Mini Shoulder Bag',
    desc: 'Color: 09 BLACK',
    url: 'https://www.uniqlo.com/us/en/products/E472032-000/00?colorDisplayCode=09',
    photoUrl: 'https://image.uniqlo.com/UQ/ST3/WesternCommon/imagesgoods/472032/sub/goods_472032_sub13_3x4.jpg'
  }
]

function WishListView (props) {
  const { wishlistId } = useParams()
  const [wishes, setWishes] = useState(null)

  const fetchWishes = () => {
    console.log(`Fetching wishes for wishlist ${wishlistId} from API`)
  }

  useEffect(() => {
    fetchWishes()
  }, [])

  return (
    <Container maxWidth='lg' sx={{ pt: 10, flexGrow: 1 }}>
      <Typography variant='h4' sx={{ mb: 3 }}>
        Jurin's Wishlist
      </Typography>
      <Box sx={{ mb: 3 }}>
        <Button
          component='label'
          variant='contained'
          startIcon={<AddIcon />}
          color='light'
          sx={{ borderRadius: 5 }}
        >
          Add New Wish
        </Button>
      </Box>
      <WishCardList wishes={testWishes} />
    </Container>
  )
}

export default WishListView

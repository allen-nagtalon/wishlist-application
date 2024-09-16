import { Box, Button, Container, Paper, styled, TextField, Typography } from '@mui/material'
import AddIcon from '@mui/icons-material/Add'
import { Modal as BaseModal } from '@mui/base/Modal'
import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import WishCardList from '../components/WishCardList/WishCardList'
import ApiInstance from '../services/ApiInstance'
import ImageUpload from '../components/ImageUpload/ImageUpload'

function WishListView (props) {
  const { wishlistId } = useParams()
  const [wishes, setWishes] = useState(null)

  const [formState, setFormState] = useState({
    title: '',
    description: '',
    url: ''
  })

  const [imageFile, setImageFile] = useState(null)

  const handleInputChange = ({ target }) => {
    setFormState({ ...formState, [target.name]: target.value })
  }

  const [modalOpen, setModalOpen] = useState(false)
  const handleOpen = () => setModalOpen(true)
  const handleClose = () => setModalOpen(false)

  const fetchWishes = () => {
    console.log(`Fetching wishes for wishlist ${wishlistId} from API`)
    ApiInstance.get(`/wishes/wishlist/${wishlistId}`)
      .then((res) => {
        setWishes(res.data.data.wishes)
      })
  }

  const handleSubmit = (event) => {
    event.preventDefault()

    ApiInstance.post('/wishes/create', {
      title: formState.title,
      description: formState.description,
      url: formState.url,
      wishlistId: wishlistId
    })
      .then((res) => {
        console.log('Wish creation:', res)

        const formData = new FormData()
        formData.append('wishId', res.data.data.result.wishId)
        formData.append('image', imageFile)

        ApiInstance.put('/image/wish/upload', formData, {
          headers: { 'Content-Type': 'multipart/form-data' }
        })
          .then((res) => {
            console.log('Image upload:', res)

            setFormState({
              title: '',
              description: '',
              url: ''
            })
            setImageFile(null)
            fetchWishes()
            setModalOpen(false)
          })
      })
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
          onClick={handleOpen}
          sx={{ borderRadius: 5 }}
        >
          Add New Wish
        </Button>
      </Box>
      <WishCardList wishes={wishes} fetchWishes={fetchWishes} />
      <Modal
        open={modalOpen}
        onClose={handleClose}
        closeAfterTransition
      >
        <Paper sx={{ p: 5 }}>
          <Box component='form' sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
            <Typography variant='h5' sx={{ pb: 3 }}>
              Add a Wish
            </Typography>
            <Box sx={{ display: 'flex', minWidth: '60dvw' }}>
              <ImageUpload setImageFile={setImageFile} />
              <Box sx={{ alignItems: 'center', display: 'flex', flexGrow: 1, flexDirection: 'column', flex: 2, ml: 3 }}>
                <TextField
                  required
                  fullWidth
                  id='title'
                  label='Title'
                  name='title'
                  value={formState.title}
                  onInput={handleInputChange}
                  autoFocus
                />
                <TextField
                  fullWidth
                  id='description'
                  label='Description'
                  name='description'
                  multiline
                  row={2}
                  value={formState.description}
                  onInput={handleInputChange}
                  sx={{ my: 2 }}
                />
                <TextField
                  fullWidth
                  id='url'
                  label='URL'
                  name='url'
                  value={formState.url}
                  onInput={handleInputChange}
                  sx={{ mb: 2 }}
                />
                <Box sx={{ display: 'flex', justifySelf: 'flex-end' }}>
                  <Button
                    variant='contained'
                    onClick={handleSubmit}
                    sx={{
                      bgcolor: 'accent.dark',
                      borderRadius: 5,
                      mr: 2
                    }}
                  >
                    Save
                  </Button>
                  <Button
                    variant='outlined'
                    onClick={handleClose}
                    sx={{
                      color: 'accent.dark',
                      borderColor: 'accent.dark',
                      borderRadius: 5
                    }}
                  >
                    Cancel
                  </Button>
                </Box>
              </Box>
            </Box>
          </Box>
        </Paper>
      </Modal>
    </Container>
  )
}

const Modal = styled(BaseModal)`
  position: fixed;
  z-index: 1300;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
`

export default WishListView

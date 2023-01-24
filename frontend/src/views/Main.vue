<script lang="js">
import { onMounted, ref } from 'vue';

onMounted( async () => {
    const response = await fetch('http://localhost:8080/api/points', {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${token}`,
    }
  });
})

</script>